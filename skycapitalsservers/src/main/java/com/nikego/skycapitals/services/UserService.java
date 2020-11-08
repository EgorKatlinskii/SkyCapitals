package com.nikego.skycapitals.services;

import com.nikego.skycapitals.Models.UserAccount;

import java.util.List;

public interface UserService {
        /**
         * Создает нового клиента
         * @param client - клиент для создания
         */

        void create(UserAccount client);


        /**
         * Возвращает список всех имеющихся клиентов
         * @return список клиентов
         */

        List<UserAccount> readAll();


        /**
         * Возвращает клиента по его ID
         * @param id - ID клиента
         * @return - объект клиента с заданным ID
         */

        UserAccount read(int id);


        /**
         * Обновляет клиента с заданным ID,
         * в соответствии с переданным клиентом
         * @param client - клиент в соответсвии с которым нужно обновить данные
         * @param id - id клиента которого нужно обновить
         * @return - true если данные были обновлены, иначе false
         */

        boolean update(UserAccount client, int id);


        /**
         * Удаляет клиента с заданным ID
         * @param id - id клиента, которого нужно удалить
         * @return - true если клиент был удален, иначе false
         */

        boolean delete(int id);

}
