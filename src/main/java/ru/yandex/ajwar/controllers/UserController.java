package ru.yandex.ajwar.controllers;

/**
 * Created by Ajwar on 12.02.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.yandex.ajwar.models.User;
import ru.yandex.ajwar.models.UserDao;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * /create  --> Создание нового пользователя в базе.
     *
     * @param name Имя пользователя
     * @param email Емэйл пользователя
     * @param online Статус пользователя
     * @param uri URI аватара пользователя
     * @return Возращает Айди пользователя или сообщение об ошибке.
     */
    @RequestMapping("/create")
    @ResponseBody
    public String create(String name, String email, boolean online, String uri) {
        User user = null;
        try {
            user = new User(name,email,online,uri);
            userDao.save(user);
            Thread.currentThread().sleep(5000);
        }
        catch (Exception e) {
            return "Ошибка при создании пользователя: " + e.toString();
        }

        return "Пользователь успешно создан! (ID = " + user.getId() + ")";
    }

    /**
     * /delete  --> Удаляет пользователя по Айди.
     *
     * @param id Айди пользователя,которого нужно удалить
     * @return Возращает строку,что пользователь успешно удален или ошибку в ином случае.
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            userDao.delete(user);
            Thread.currentThread().sleep(5000);
        }
        catch (Exception e) {
            return "Ошибка при удалении пользователя: " + e.toString();
        }
        return "Пользователь успешно удален!";
    }

    /**
     * /get-by-id  --> Возращает пользователя по Айди.
     *
     * @param id Поиск в базе по Айди пользователя.
     * @return Возвращает персональные данные в формате Json или что такого пользователя не существует.
     */
    @RequestMapping("/get-by-id")
    @ResponseBody
    public String getById(Long id) {
        User user;
        try {
            user = userDao.findById(id);
            Thread.currentThread().sleep(5000);
        }
        catch (Exception e) {
            return "Пользователя с таким ID не существует.";
        }
        return "Персональные данные пользователя:"+user.toString();
    }

    /**
     * /update  --> Обновляет статус пользователя по его айди.
     *
     * @param id Айди пользовталя для обновления.
     * @param online Новый статус.
     * @return Возвращает персональные данные в формате Json.
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(long id,boolean online) {
        boolean oldStatus;
        try {
            User user = userDao.findOne(id);
            oldStatus=user.isOnline();
            user.setOnline(online);
            userDao.save(user);
            Thread.currentThread().sleep(5000);
        }
        catch (Exception e) {
            return "Ошибка при обновлении пользователя: " + e.toString();
        }
        return "Пользователь успешно обновлен!  {'ID'="+id+",'Предыдущий статус'="+(oldStatus?"Online":"Offline")+",'Новый статус'="+(online?"Online":"Offline")+"}";
    }

}