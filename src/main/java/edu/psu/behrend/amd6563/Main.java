package edu.psu.behrend.amd6563;

import java.sql.*;


public class Main
{
    public static void main(String[] args)
    {
        Connection connection = null;

        try
        {
            String url = "jdbc:sqlite:/Users/alecdady/Desktop/Lab11/src/main/resources/mybookstore.dms";

            connection = DriverManager.getConnection(url);

            System.out.println("Connection established!");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        //Gets all books that have a price of $19.99
        String sql1 = "SELECT titles.title FROM titles WHERE titles.price = 19.99";
        //Selects all authors that first name's start with M
        String sql2 = "SELECT authors.au_fname FROM authors WHERE authors.au_fname LIKE 'M%'";
        //Insets JK Rowling into the authors table
        String sql3 = "INSERT INTO authors VALUES(24, 'Rowling', 'J.K','555-555-5555','123 Street street','somewhere','in the world','11111')";
        //Changes Publisher name from GG&G to Harper and Rowe
        String sql4 = "UPDATE publishers SET publishers.pub_name = 'Harper and Rowe' WHERE publishers.pub_name = 'GG&G'";
        //Deletes the Book at index 24
        String sql5 = "DELETE FROM titles WHERE titles.au_id = 24";

        try
        {
            //#1
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            //preparedStatement.setString( 0,"Titles of books that are $19.99" );
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                System.out.println(resultSet.getString("title"));
            }
            resultSet.close();
            //#2
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            //preparedStatement2.setString( 0, "Author's names that start with M" );
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            System.out.println("Author's names that start with M");
            while(resultSet2.next())
            {
                System.out.println(resultSet2.getString("au_fname"));
            }
            resultSet2.close();
            //#3
            PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
            //preparedStatement3.setString( , "JK Rowling has been inserted" );
            preparedStatement3.executeUpdate();
            System.out.println("JK Rowling has been inserted");

            //#4
            PreparedStatement preparedStatement4 = connection.prepareStatement(sql4);
            //preparedStatement4.setString( 1, "Changed publisher GG&G to Harper and Row" );
            preparedStatement4.executeUpdate();

            System.out.println("Changed publisher GG&G to Harper and Row");

            //#5
            PreparedStatement preparedStatement5 = connection.prepareStatement(sql5);
            //preparedStatement5.setString( 1, "Deleted title at index of 24" );
            preparedStatement5.executeUpdate();
            System.out.println("Deleted title at index of 24");


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }
}