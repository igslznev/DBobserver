package main.controller

import main.mail.MyAuthenticator
import java.time.LocalDate
import java.util.*
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import javax.swing.JOptionPane

class MailController {
    val username = "observerdb@gmail.com"
    val password = "951357654"

    fun sendLetter(to: String, subject: String, text: String) {
        val props = Properties()

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        val session = Session.getInstance(props,
                MyAuthenticator(username, password))
        try {


            // Создание объекта сообщения
            val msg = MimeMessage(session)

            // Установка атрибутов сообщения
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to))
            msg.subject = subject

            // Установка тела сообщения
            msg.setText(text)

            // Отправка сообщения
            Transport.send(msg)

        } catch (e: MessagingException) {
            JOptionPane.showMessageDialog(null, "Письмо не было отправлено!", "Ошибка!", JOptionPane.ERROR_MESSAGE)
        }
    }

    fun sendLetterOnCabinetChange(emailFirstAgent: String,firstNewCabinet: Int, emailSecondAgent: String, secondNewCabinet: Int) {
        var subject = "Смена кабинета"
        var textFirst = "Спешим уведомить Вас о смене кабинета. Ваш новый кабинет №"
        var textSecond = textFirst

        textFirst += firstNewCabinet.toString();
        textSecond += secondNewCabinet.toString();

        val endMessage = ".\n\nУдачного Вам дня!"
        textFirst += endMessage
        textSecond += endMessage
        sendLetter(emailFirstAgent, subject, textFirst)
        sendLetter(emailSecondAgent,subject, textSecond)
    }

    fun sendLetterOnAddContract(price: String, startDate: LocalDate, expDate: LocalDate, clientName: String,
                                agentMail: String, type: String) {
        var subject = "Новый контракт"
        var text = "Был создан новый контракт на сумму " + price + " на период с " + startDate.toString() +
                " до " + expDate.toString() + ".\nТип контракта - " + type + ".\nИмя клиента - " + clientName + "."
        sendLetter(agentMail, subject,text)
    }

}