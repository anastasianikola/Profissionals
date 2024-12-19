package com.example.matulewithstyle.validation

class Validator {
    companion object {
        private val emailRegex = "^[a-z0-9]+@[a-z0-9]+\\.[a-z]{2,}$".toRegex()
        private val passwordRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$".toRegex()

        /**
         * Проверяет валидность email.
         * @param email Строка, содержащая email.
         * @return true, если email валиден, иначе false.
         */
        fun isValidEmail(email: String): Boolean {
            return email.matches(emailRegex)
        }

        /**
         * Проверяет валидность пароля.
         * Пароль должен содержать:
         * - Минимум 8 символов.
         * - Как минимум одну заглавную букву, одну строчную букву и одну цифру.
         * @param password Строка, содержащая пароль.
         * @return true, если пароль валиден, иначе false.
         */
        fun isValidPassword(password: String): Boolean {
            return password.matches(passwordRegex)
        }
    }
}