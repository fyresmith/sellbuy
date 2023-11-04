package com.peach.sellbuy_ecommerce.business;

import com.peach.sellbuy_ecommerce.util.Data;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;

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
     * Constructs a DatabaseAccess instance.
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

    /**
     * Selects a database record by its primary key.
     *
     * @param PK The primary key value to search for.
     * @return The object retrieved from the database or null if not found.
     */
    public T select(int PK) {
        String selectQuery = "SELECT * FROM " + tableName + " WHERE " + primaryKey + " = ?";
        T result = null;

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, PK);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                result = createObjectFromResultSet(rs);
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
                try {
                    Object value = rs.getObject(fieldName);
                    field.set(result, value);
                } catch (SQLException ignored) {}
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
        String saveQuery = generateInsertQuery(item);

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            bindObjectToPreparedStatement(preparedStatement, item);
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
        try (Connection connection = DriverManager.getConnection(Data.DATABASE)) {
            // Attempt to execute a query to select the specified column.
            String sql = "SELECT " + columnName + " FROM " + this.tableName + " WHERE 1=0";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.executeQuery();
            }
            return true; // No exception, so the column exists.
        } catch (SQLException e) {
            // An exception occurred, indicating that the column doesn't exist.
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
        StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (");
        StringBuilder values = new StringBuilder(") VALUES (");

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();

            if (isColumnInDatabase(fieldName)) {
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

        String updateQuery = "UPDATE " + tableName + " SET ";
        Field[] fields = item.getClass().getDeclaredFields();

        try (Connection connection = DriverManager.getConnection(Data.DATABASE)) {
            for (Field field : fields) {
                field.setAccessible(true);
                String fieldName = field.getName();
                if (isColumnInDatabase(fieldName)) {
                    updateQuery += fieldName + " = ?, ";
                }
            }

            updateQuery = updateQuery.substring(0, updateQuery.length() - 2); // Remove the trailing comma and space
            updateQuery += " WHERE " + primaryKey + " = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
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
            }
        } catch (SQLException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
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

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
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
     * @param altPK The alternate primary key column name to search.
     * @param value The value to search for in the specified column.
     * @return An object representing the row found, or null if not found.
     */
    public T alternateSelect(String altPK, String value) {
        String selectQuery = "SELECT * FROM " + this.tableName + " WHERE " + altPK + " = ?";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
     * @param columnName The name of the column to search for the value.
     * @param value The value to check for in the specified column.
     * @return True if the value exists in the column; false otherwise.
     */
    public boolean valueExists(String columnName, String value) {
        String selectQuery = "SELECT 1 FROM " + tableName + " WHERE " + columnName + " = ?";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
     * @return A list of objects representing all rows in the table.
     */
    public LinkedList<T> getTable() {
        LinkedList<T> resultList = new LinkedList<>();

        String selectQuery = "SELECT * FROM " + this.tableName;

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
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

    /**
     * Retrieves a random string value from a specified column in the database table.
     * @param columnName The name of the column to retrieve a random string from.
     * @return A random string value from the specified column, or null if not found.
     */
    public String getRandomStringFromColumn(String columnName) {
        String selectQuery = "SELECT " + columnName + " FROM " + tableName + " ORDER BY RAND() LIMIT 1";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
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
     * @param columnName The name of the column to retrieve a random integer from.
     * @return A random integer value from the specified column, or 0 if not found.
     */
    public int getRandomIntegerFromColumn(String columnName) {
        String selectQuery = "SELECT " + columnName + " FROM " + tableName + " ORDER BY RAND() LIMIT 1";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
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
     * @return The number of rows in the table, or 0 if an error occurs.
     */
    public int getRowCount() {
        String countQuery = "SELECT COUNT(*) FROM " + this.tableName;

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
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
     * @param PK The primary key value to search for.
     * @return A list of objects representing the rows with the given primary key value.
     */
    public LinkedList<T> getRows(int PK) {
        LinkedList<T> resultList = new LinkedList<>();

        String selectQuery = "SELECT * FROM " + this.tableName + " WHERE " + this.primaryKey + " = ?";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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

    public LinkedList<T> search(String keyword) {
        LinkedList<T> products = new LinkedList<>();

        String query = "SELECT * FROM product WHERE (productName LIKE ? OR description LIKE ? OR productCategory LIKE ? OR keywords LIKE ?) LIMIT 700";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
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

    /**
     * Retrieves all rows in the database table with the specified value in the specified column.
     * @param columnName The name of the column to search for the value.
     * @param PK The value to search for in the specified column.
     * @return A list of objects representing the rows with the given value in the specified column.
     */
    public LinkedList<T> getAltRows(String columnName, int PK) {
        LinkedList<T> resultList = new LinkedList<>();

        String selectQuery = "SELECT * FROM " + this.tableName + " WHERE " + columnName + " = ?";

        try (Connection connection = DriverManager.getConnection(Data.DATABASE);
             PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
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
     * @return The new unique primary key value, or -1 if an error occurs.
     */
    public int generateUniquePK() {
        int newPK = -1;

        // Establish a database connection.
        try (Connection connection = DriverManager.getConnection(Data.DATABASE)) {
            // Create a SQL query to retrieve the next unique PK value.
            String selectQuery = "SELECT MAX(" + this.primaryKey + ") + 1 FROM " + this.tableName;
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
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

        try (Connection connection = DriverManager.getConnection(Data.DATABASE)) {
            String query = "SELECT COUNT(*) FROM " + this.tableName;

            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
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