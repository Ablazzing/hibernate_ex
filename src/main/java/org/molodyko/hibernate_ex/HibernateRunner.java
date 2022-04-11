package org.molodyko.hibernate_ex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.molodyko.hibernate_ex.entity.Category;
import org.molodyko.hibernate_ex.entity.Holiday;
import org.molodyko.hibernate_ex.entity.HolidayType;
import org.molodyko.hibernate_ex.entity.User;
import org.molodyko.hibernate_ex.enums.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class HibernateRunner {
    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);
    private static Configuration configuration;

    public static void main(String[] args) {
        log.trace("Hibernate Runner start!");

        initConfig();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            createUser(session);
            createCategory(session);
            createHolidayType(session);
            createHoliday(session);
            session.getTransaction().commit();
        }
    }

    private static void initConfig() {
        configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(HolidayType.class);
        configuration.addAnnotatedClass(Holiday.class);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.configure();
    }

    private static void createUser(Session session) {
        User user = User.builder()
                .username("ablazzing")
                .email("y288@ay.ru")
                .role(UserRole.ADMIN)
                .password("kkkk")
                .build();
        session.save(user);
    }

    private static void createCategory(Session session) {
        Category category = Category.builder()
                .name("Еда")
                .userId(1)
                .build();
        session.save(category);
    }

    private static void createHolidayType(Session session) {
        HolidayType holidayType = HolidayType.builder()
                .categoryId(1)
                .name("Отпуск")
                .build();
        session.save(holidayType);
    }

    private static void createHoliday(Session session) {
        Holiday holiday = Holiday.builder()
                .holidayTypeId(1)
                .dateStart(LocalDate.of(2020, 10, 1))
                .dateEnd(LocalDate.of(2020, 10, 10))
                .userId(1)
                .build();
        session.save(holiday);
    }
}
