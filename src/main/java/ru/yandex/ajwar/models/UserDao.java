package ru.yandex.ajwar.models;

/**
 * Created by Ajwar on 12.02.2017.
 */
import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.JpaRepository;


@Transactional
public interface UserDao extends JpaRepository<User, Long> {

    /**
     * Возвращает персональные данные пользователя или сообщение, что такого пользователя не существует.
     *
     * @param id айди пользователя.
     */
    User findById(Long id);

}