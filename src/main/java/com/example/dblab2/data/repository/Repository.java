package com.example.dblab2.data.repository;

import com.example.dblab2.data.dto.Apartment;
import com.example.dblab2.data.dto.Clazz;
import com.example.dblab2.data.dto.Status;
import com.example.dblab2.data.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Repository {
        @Autowired
        private JdbcTemplate jdbcTemplate;

        public List<User> findUserById(Long id){
                String sql = "SELECT * FROM usr where id = ?";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setUsername(rs.getString("username"));
                        return user;
                }, id);
        }

        //Знайти усі апартаменти які знаходяться в обраному статусі на проміжку обраного часу.
        public List<Integer> query1(LocalDate arrival, LocalDate end, String status){
                String sql = "SELECT DISTINCT a.id FROM apartment a LEFT JOIN apartment_status as2 " +
                        "ON a.id = as2.apartment_id " +
                        "WHERE (as2.arrival_day <= ? AND as2.end_day >= ?) AND (as2.pay_time_limit IS NULL OR as2.pay_time_limit >=NOW()) " +
                        "AND (as2.status = ?)";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        return rs.getInt("id");
                }, arrival, end, status);
        }

        //Знайти усі апартаменти оплачені користувачем К1.
        public List<Integer> query2(String username){
                String sql = "SELECT DISTINCT a.id FROM apartment a LEFT JOIN apartment_status as2 " +
                        "ON a.id = as2.apartment_id AND as2.status = 'BOUGHT' LEFT JOIN usr u on as2.user_id = u.id AND u.username = ?";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        return rs.getInt("id");
                }, username);
        }

        //Обрати користувачів що мають бронювання на усі номери.
        public List<String> query3(){
                String sql = "SELECT u.id, u.username " +
                        "FROM usr u WHERE u.id IN(" +
                        "SELECT as2.user_id FROM apartment_status as2 GROUP BY " +
                        "as2.user_id HAVING COUNT(as2.apartment_id) = " +
                        "(SELECT COUNT(a.id) FROM apartment a));";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        return "{id: " + rs.getInt("id") + "; username: " + rs.getString("username") + "}";
                });
        }


        //Обрати номери в яких будуть зупиняться прийнамні усі люди що зупиняються в номері Н1.
        public List<Integer> query4(Integer apartmentId){
                String sql =
                        "SELECT a.id FROM apartment a " +
                        "WHERE NOT EXISTS " +
                           "(SELECT as2.user_id FROM apartment_status as2 " +
                            "WHERE as2.apartment_id = ? AND as2.user_id NOT IN " +
                                 "(SELECT as3.user_id FROM apartment_status as3 WHERE as3.apartment_id = a.id))";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        return rs.getInt("id");
                }, apartmentId);
        }

        //Обрати усіх користувачів що мають ті і тільки ті ролі що і користувач К1
        public List<String> query5(String username){
                String sql = "SELECT * FROM usr u WHERE NOT EXISTS (SELECT ur.role_id FROM user_role ur " +
                        "WHERE ur.user_id = u.id AND ur.role_id NOT IN (" +
                        "SELECT ur2.role_id FROM user_role ur2 JOIN usr u2 ON ur2.user_id = u2.id " +
                        "WHERE u2.username = ?)) " +
                        "AND (SELECT COUNT(*) FROM user_role ur3 WHERE ur3.user_id = u.id) = " +
                        "(SELECT COUNT(*) FROM user_role ur3 JOIN usr u4 ON ur3.user_id = u4.id  WHERE u4.username  = ?)";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        return "{id: " + rs.getInt("id") + "; username: " + rs.getString("username") + "}";
                }, username, username);
        }








}

