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

        public List<Apartment> query1(LocalDate arrival, LocalDate end, String status){
                String sql = "SELECT DISTINCT * FROM apartment a LEFT JOIN apartment_status as2 " +
                        "ON a.id = as2.apartment_id " +
                        "AND (as2.arrival_day <= ? AND as2.end_day >= ?) AND (as2.pay_time_limit IS NULL OR as2.pay_time_limit >=NOW()) " +
                        "WHERE (as2.status = ?)";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        Apartment apartment = new Apartment();
                        apartment.setId(rs.getInt("id"));
                        apartment.setBeds(rs.getInt("beds"));
                        apartment.setClazz(Clazz.values()[rs.getInt("clazz")]);
                        apartment.setPrice(rs.getInt("price"));
                        return apartment;
                }, arrival, end, status);
        }

        //Знайти усі апартаменти оплачені користувачем К1.
        public List<Apartment> query2(String username){
                String sql = "SELECT DISTINCT * apartment a LEFT JOIN apartment_status as2 " +
                        "ON a.id = as2.apartment_id AND as.status = 'BOUGHT' LEFT JOIN usr u on as2.user_id = u.user_id AND u.user_name = ?";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        Apartment apartment = new Apartment();
                        apartment.setId(rs.getInt("id"));
                        apartment.setBeds(rs.getInt("beds"));
                        apartment.setClazz(Clazz.valueOf(rs.getString("clazz")));
                        apartment.setPrice(rs.getInt("price"));
                        return apartment;
                }, username);
        }

        //Обрати користувачів що мають бронювання на усі номери.
        public List<User> query3(){
                String sql = "SELECT u.id, u.username " +
                        "FROM usr u WHERE u.id IN(" +
                        "SELECT as2.user_idFROM apartment_status as2 GROUP BY " +
                        "as2.user_idHAVING COUNT(as2.apartment_id) = " +
                        "(SELECT COUNT(a.id)FROM apartment a));";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        User user = new User();
                        user.setId((long) rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        return user;
                });
        }


        //Обрати номери в яких є будуть зупиняться прийнамні усі люди що зупиняються в номері Н1.
        public List<Apartment> query4(String username){
                String sql = "";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        Apartment apartment = new Apartment();
                        apartment.setId(rs.getInt("id"));
                        apartment.setBeds(rs.getInt("beds"));
                        apartment.setClazz(Clazz.valueOf(rs.getString("clazz")));
                        apartment.setPrice(rs.getInt("price"));
                        return apartment;
                }, username);
        }

        //Обрати усіх користувачів що мають ті і тільки ті ролі що і користувач К1
        public List<Apartment> query5(String username){
                String sql = "SELECT * FROM usr u WHERE NOT EXISTS (SELECT ur.role_id FROM user_role ur " +
                        "WHERE ur.user_id = u.id AND ur.role_id NOT IN (" +
                        "SELECT ur2.role_id FROM user_role ur2 JOIN usr u2 ON ur2.user_id = u2.id " +
                        "WHERE u2.username = 'User 1'))AND (SELECT COUNT(*) FROM user_role ur3 WHERE ur3.user_id = u.id) =" +
                        "(SELECT COUNT(*) FROM user_role ur3 JOIN usr u4 ON ur3.user_id = u4.id  WHERE u4.username  = 'User 1')";
                return  jdbcTemplate.query(sql, (rs, rowNum) -> {
                        Apartment apartment = new Apartment();
                        apartment.setId(rs.getInt("id"));
                        apartment.setBeds(rs.getInt("beds"));
                        apartment.setClazz(Clazz.valueOf(rs.getString("clazz")));
                        apartment.setPrice(rs.getInt("price"));
                        return apartment;
                }, username);
        }








}

