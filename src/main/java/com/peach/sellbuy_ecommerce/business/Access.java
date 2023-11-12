package com.peach.sellbuy_ecommerce.business;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.Objects;

/**
 * This class provides database access and manipulation operations, including select, save, update, and delete.
 *
 * @param <T> The type of objects to work with in the database.
 */
public class Access<T> {
    private final String tableName;
    private final String primaryKey;
    private Class<T> resultClass;

    /**
     * Constructs an Access instance.
     *
     * @param tableName   The name of the database table.
     * @param primaryKey  The primary key column name.
     * @param resultClass The class representing the object to work with in the database.
     */
    public Access(String tableName, String primaryKey, Class<T> resultClass) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.resultClass = resultClass;
    }

    public Access(Class<T> resultClass) {
        this.resultClass = resultClass;
        if (resultClass == User.class) {
            this.tableName = "user";
            this.primaryKey = "userID";
        } else if (resultClass == Order.class) {
            this.tableName = "order";
            this.primaryKey = "orderID";
        } else if (resultClass == OrderItem.class) {
            this.tableName = "order_item";
            this.primaryKey = "orderItemID";
        } else if (resultClass == Review.class) {
            this.tableName = "review";
            this.primaryKey = "reviewID";
        } else if (resultClass == Image.class) {
            this.tableName = "image";
            this.primaryKey = "imageID";
        } else {
            this.tableName = "product";
            this.primaryKey = "productID";
        }

    }

    public T select(int PK) {
        String query = "SELECT * FROM " + this.tableName + " WHERE " + this.primaryKey + " = ?";
        T result = null;

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, PK);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result = this.createObjectFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Creates an object from a ResultSet.
     *
     * @param rs The ResultSet containing data.
     * @return The object created from the ResultSet.
     * @throws SQLException if there is a database-related error.
     */
    private T createObjectFromResultSet(ResultSet rs) {
        try {
            T result = this.resultClass.getDeclaredConstructor().newInstance();
            Field[] fields = this.resultClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                // There's a reason for this...
                try {
                    Object value = rs.getObject(fieldName);
                    field.set(result, value);
                } catch (SQLException ignored) {
                }
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Saves an object to the database.
     *
     * @param item The object to save.
     * @return true if the save operation is successful, false otherwise.
     */
    public boolean save(T item) {
        String query = this.generateInsertQuery(item);

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            this.bindObjectToPreparedStatement(preparedStatement, item);
            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if a column with the given name exists in the database for the specified table.
     *
     * @param columnName The name of the column to check.
     * @return true if the column exists, false otherwise.
     */
    private boolean isColumnInDatabase(String columnName) {
        try {
            // Attempt to execute a query to select the specified column.
            String query = "SELECT " + columnName + " FROM " + this.tableName + " WHERE 1=0";
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            return true;
        } catch (SQLException var4) {
            return false;
        }
    }

    /**
     * Generates an SQL insert query for the provided object.
     *
     * @param item The object for which to generate the insert query.
     * @return The generated SQL insert query.
     */
    private String generateInsertQuery(T item) {
        Field[] fields = item.getClass().getDeclaredFields();
        StringBuilder query = new StringBuilder("INSERT INTO " + this.tableName + " (");
        StringBuilder values = new StringBuilder(") VALUES (");

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (this.isColumnInDatabase(fieldName)) {
                query.append(field.getName()).append(", ");
                values.append("?, ");
            }
        }

        query.setLength(query.length() - 2); // Remove the trailing ", "
        values.setLength(values.length() - 2); // Remove the trailing ", "
        query.append(values).append(")");

        return query.toString();
    }

    /**
     * Binds an object's fields to a PreparedStatement for saving to the database.
     *
     * @param preparedStatement The PreparedStatement to bind fields to.
     * @param item              The object whose fields need to be bound.
     * @throws SQLException if there is a database-related error.
     */
    private void bindObjectToPreparedStatement(PreparedStatement preparedStatement, T item) throws SQLException {
        Field[] fields = item.getClass().getDeclaredFields();
        int parameterIndex = 1;

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();

            // Check if the field has a corresponding column in the database
            if (isColumnInDatabase(fieldName)) {
                Object value;

                try {
                    value = field.get(item);
                    preparedStatement.setObject(parameterIndex++, value); // Parameters are 1-indexed
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Updates a database record with the data from the provided object.
     *
     * @param item The object with updated data.
     * @return true if the update operation is successful, false otherwise.
     */
    public boolean update(T item) {
        if (item == null) {
            System.out.println("Update failed: Provided object is null.");
            return false;
        }

        String query = "UPDATE " + tableName + " SET ";
        Field[] fields = item.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if (isColumnInDatabase(fieldName)) {
                query += fieldName + " = ?, ";
            }
        }

        query = query.substring(0, query.length() - 2); // Remove the trailing comma and space
        query += " WHERE " + primaryKey + " = ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            int parameterIndex = 1;
            for (Field field : fields) {
                Object value = field.get(item);
                String fieldName = field.getName();
                if (isColumnInDatabase(fieldName)) {
                    preparedStatement.setObject(parameterIndex, value);
                    parameterIndex++;
                }
            }

            // Set the primary key value
            Field primaryKeyField = item.getClass().getDeclaredField(primaryKey);
            primaryKeyField.setAccessible(true);
            preparedStatement.setObject(parameterIndex, primaryKeyField.get(item));

            int result = preparedStatement.executeUpdate();

            if (result == 1) {
                System.out.println("Update successful!");
                return true;
            } else {
                System.out.println("Update failed :/");
            }
        } catch (SQLException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException |
                 SecurityException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * Deletes a database record by its primary key.
     *
     * @param PK The primary key value of the record to delete.
     * @return true if the delete operation is successful, false otherwise.
     */
    public boolean delete(int PK) {
        String deleteQuery = "DELETE FROM " + tableName + " WHERE " + primaryKey + " = ?";

        try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, PK);
            int result = preparedStatement.executeUpdate();

            return result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Copies the fields from one object to another.
     *
     * @param source      The source object to copy fields from.
     * @param destination The destination object to copy fields to.
     * @throws IllegalAccessException if field access is not allowed.
     */
    public static <T> void copyFields(T source, T destination) throws IllegalAccessException {
        // Get the class of the source and destination objects
        if (source == null) {
            destination = null;
            return;
        }

        Class<?> sourceClass = source.getClass();
        Class<?> destinationClass = destination.getClass();

        // Iterate through all fields in the source object
        for (Field sourceField : sourceClass.getDeclaredFields()) {
            // Make the field accessible (even if it's private)
            sourceField.setAccessible(true);

            // Find the corresponding field in the destination object
            try {
                Field destinationField = destinationClass.getDeclaredField(sourceField.getName());

                // Make the destination field accessible
                destinationField.setAccessible(true);

                // Get the value from the source field and set it in the destination field
                Object value = sourceField.get(source);
                destinationField.set(destination, value);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Performs a database query to select a row by an alternate primary key and return the associated object.
     *
     * @param altPK The alternate primary key column name to search.
     * @param value The value to search for in the specified column.
     * @return An object representing the row found, or null if not found.
     */
    public T altSelect(String altPK, String value) {
        String query = "SELECT * FROM " + this.tableName + " WHERE " + altPK + " = ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, value);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return createObjectFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if the value is not found in the column.
    }

    /**
     * Checks if a value exists in a specified column of the database table.
     *
     * @param columnName The name of the column to search for the value.
     * @param value      The value to check for in the specified column.
     * @return True if the value exists in the column; false otherwise.
     */
    public boolean existsInColumn(String columnName, String value) {
        String query = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, value);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a row with the value exists in the column.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Error occurred or value doesn't exist.
        }
    }

    /**
     * Retrieves all rows from the database table.
     *
     * @return A list of objects representing all rows in the table.
     */
    public LinkedList<T> getTable() {
        LinkedList<T> resultList = new LinkedList<>();

        String query = "SELECT * FROM " + this.tableName;

        try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                T rowData = createObjectFromResultSet(resultSet);
                resultList.add(rowData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public LinkedList<T> getRows(int start, int end) {
        LinkedList<T> resultList = new LinkedList();
        if (start >= 0 && start < end) {
            String query = "SELECT * FROM " + this.tableName + " LIMIT ? OFFSET ?";

            try {
                PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);

                try {
                    preparedStatement.setInt(1, end - start);
                    preparedStatement.setInt(2, start);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    try {
                        while(resultSet.next()) {
                            T rowData = this.createObjectFromResultSet(resultSet);
                            resultList.add(rowData);
                        }
                    } catch (Throwable var11) {
                        if (resultSet != null) {
                            try {
                                resultSet.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (resultSet != null) {
                        resultSet.close();
                    }
                } catch (Throwable var12) {
                    if (preparedStatement != null) {
                        try {
                            preparedStatement.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException var13) {
                var13.printStackTrace();
            }

            return resultList;
        } else {
            throw new IllegalArgumentException("Invalid range specified.");
        }
    }

    public LinkedList<Integer> getColumn(String columnName) {
        LinkedList<Integer> columnValues = new LinkedList<>();
        String query = "SELECT " + columnName + " FROM " + this.tableName;

        try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int value = resultSet.getInt(columnName);
                columnValues.add(value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return columnValues;
    }

    /**
     * Retrieves a random string value from a specified column in the database table.
     *
     * @param columnName The name of the column to retrieve a random string from.
     * @return A random string value from the specified column, or null if not found.
     */
    public String getRandomStringFromColumn(String columnName) {
        String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY RAND() LIMIT 1";

        try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getString(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves a random integer value from a specified column in the database table.
     *
     * @param columnName The name of the column to retrieve a random integer from.
     * @return A random integer value from the specified column, or 0 if not found.
     */
    public int getRandomIntegerFromColumn(String columnName) {
        String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY RAND() LIMIT 1";

        try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(columnName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * Gets the total number of rows in the database table.
     *
     * @return The number of rows in the table, or 0 if an error occurs.
     */
    public int getRowCount() {
        String countQuery = "SELECT COUNT(*) FROM " + this.tableName;

        try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(countQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Default to 0 if there is an error.
    }

    /**
     * Retrieves all rows in the database table with the specified primary key value.
     *
     * @param PK The primary key value to search for.
     * @return A list of objects representing the rows with the given primary key value.
     */
    public LinkedList<T> getRows(int PK) {
        LinkedList<T> resultList = new LinkedList<>();

        String query = "SELECT * FROM " + this.tableName + " WHERE " + this.primaryKey + " = ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, PK);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    resultList.add(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public LinkedList<T> searchAndSortByReversedPrice(String keyword) {
        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product WHERE (productName LIKE ? OR description LIKE ? OR productCategory LIKE ? OR keywords LIKE ?) ORDER BY price ASC LIMIT 700";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            String keywordPattern = "%" + keyword + "%";
            preparedStatement.setString(1, keywordPattern);
            preparedStatement.setString(2, keywordPattern);
            preparedStatement.setString(3, keywordPattern);
            preparedStatement.setString(4, keywordPattern);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    products.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }


    public LinkedList<T> searchAndSortByPrice(String keyword) {
        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product WHERE (productName LIKE ? OR description LIKE ? OR productCategory LIKE ? OR keywords LIKE ?) ORDER BY price DESC LIMIT 700";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            String keywordPattern = "%" + keyword + "%";
            preparedStatement.setString(1, keywordPattern);
            preparedStatement.setString(2, keywordPattern);
            preparedStatement.setString(3, keywordPattern);
            preparedStatement.setString(4, keywordPattern);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    products.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public LinkedList<T> search(String keyword, double minPrice, double maxPrice) {
        if (!Objects.equals(this.tableName, "product")) {
            return new LinkedList<>();
        }

        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product WHERE (productName LIKE ? OR description LIKE ? OR productCategory LIKE ? OR keywords LIKE ?) AND (price >= ? AND price <= ?) LIMIT 700";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            String keywordPattern = "%" + keyword + "%";
            preparedStatement.setString(1, keywordPattern);
            preparedStatement.setString(2, keywordPattern);
            preparedStatement.setString(3, keywordPattern);
            preparedStatement.setString(4, keywordPattern);
            preparedStatement.setDouble(5, minPrice);
            preparedStatement.setDouble(6, maxPrice);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    products.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public LinkedList<T> search(String keyword) {
        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product WHERE (productName LIKE ? OR description LIKE ? OR productCategory LIKE ? OR keywords LIKE ?) LIMIT 700";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            String keywordPattern = "%" + keyword + "%";
            preparedStatement.setString(1, keywordPattern);
            preparedStatement.setString(2, keywordPattern);
            preparedStatement.setString(3, keywordPattern);
            preparedStatement.setString(4, keywordPattern);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    products.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public LinkedList<T> search(String keyword, int limit) {
        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product WHERE productName LIKE ? OR description LIKE ? OR productCategory LIKE ? OR keywords LIKE ? LIMIT ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            String keywordPattern = "%" + keyword + "%";
            preparedStatement.setString(1, keywordPattern);
            preparedStatement.setString(2, keywordPattern);
            preparedStatement.setString(3, keywordPattern);
            preparedStatement.setString(4, keywordPattern);
            preparedStatement.setInt(5, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    products.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public LinkedList<String> getProductCategories() {
        if (!Objects.equals(this.tableName, "product")) {
            return new LinkedList<>();
        }

        LinkedList<String> productCategories = new LinkedList<>();

        String query = "SELECT productCategory, COUNT(*) as categoryCount FROM product GROUP BY productCategory ORDER BY categoryCount DESC";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String productCategory = resultSet.getString("productCategory");
                    productCategories.add(productCategory);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productCategories;
    }

    public LinkedList<T> getRandomObjects(int limit) {
        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product ORDER BY RAND() LIMIT ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, limit);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    products.add(rowData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    /**
     * Retrieves all rows in the database table with the specified value in the specified column.
     *
     * @param columnName The name of the column to search for the value.
     * @param PK         The value to search for in the specified column.
     * @return A list of objects representing the rows with the given value in the specified column.
     */
    public LinkedList<T> getAltRows(String columnName, int PK) {
        LinkedList<T> resultList = new LinkedList<>();

        String query = "SELECT * FROM " + this.tableName + " WHERE " + columnName + " = ?";

        try {
            PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, PK);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    T rowData = createObjectFromResultSet(resultSet);
                    resultList.add(rowData);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    /**
     * Generates a new unique primary key for the database table.
     *
     * @return The new unique primary key value, or -1 if an error occurs.
     */
    public int generateUniquePK() {
        int newPK = -1;

        // Establish a database connection.
        try {
            // Create a SQL query to retrieve the next unique PK value.
            String query = "SELECT MAX(" + this.primaryKey + ") + 1 FROM " + this.tableName;
            try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    newPK = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (newPK < 1) {
            newPK = 1;
        }

        return newPK;
    }

    public int size() {
        int tableSize = 0;

        try {
            String query = "SELECT COUNT(*) FROM " + this.tableName;

            try (PreparedStatement preparedStatement = DB.getInstance().getConnection().prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    tableSize = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tableSize;
    }
}