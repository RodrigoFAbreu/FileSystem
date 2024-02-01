package org.cdb.filesystem.database;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class DatabaseConnectionTest
{

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection()
    {
        assertThat(dataSource).isNotNull();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // Execute a query to ensure the connection works
        Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
        assertThat(result).isEqualTo(1);
    }
}