package com.example.demo.student;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfStudentEmail() {

        String email = "jamila@gmail.com";
        Student student = new Student("Jamila", email, Gender.FEMALE);
        underTest.save(student);

        Boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfStudentEmailDoesNotExists() {

        String email = "jamila@gmail.com";

        Boolean expected = underTest.selectExistsEmail(email);

        assertThat(expected).isFalse();
    }
}