import java.sql.*;

class task1 {
    public static void main(String args[])
            throws SQLException, ClassNotFoundException {

////        final long start = System.currentTimeMillis();
//
//        Class.forName("oracle.jdbc.driver.OracleDriver");
//        Connection conn = DriverManager.getConnection
//                ("jdbc:oracle:thin:@localhost:1521:db", "tpchr", "oracle");
//        try {
//            System.out.println("Just started");
////            Statement stmt = conn.createStatement();
////            ResultSet rset = stmt.executeQuery("SELECT * FROM LINEITEM");
//
//            PreparedStatement pstmt = conn.prepareStatement("SELECT L_EXTENDEDPRICE FROM LINEITEM");
//            ResultSet rset = pstmt.executeQuery();
//
//            int counter = 0;
//            double l_exp = 0.0;
//            while (rset.next()) {
//                l_exp = l_exp + rset.getFloat("L_EXTENDEDPRICE");
//                counter++;
//                if (counter % 100000 == 0)
//                    System.out.println("Working hard");
//            }
//            System.out.println(l_exp / counter);
//        } catch (SQLException e) {
//            String errmsg = e.getMessage();
//            System.out.println(errmsg);
//        }

//        final long durationInMilliseconds = System.currentTimeMillis()-start;
//        System.out.println("executeLongRunningTask() took " + durationInMilliseconds + "ms.");
        Double price = Double.parseDouble("5") * 100;
        System.out.println("hello"+price.intValue());
    }
}
