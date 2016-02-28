package main.transaction

import main.controller.MailController
import main.controller.DBController
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import java.sql.SQLException
import javax.mail.MessagingException
import javax.mail.SendFailedException
import javax.swing.JOptionPane


class ChangeCabinets constructor(val dbController: main.controller.DBController) {

    companion object {
        val mailController = MailController()
    }


    fun changeCabinetsTransaction(firstAgentId: Int, secondAgentId: Int) {
        try {
            var firstCabinet = 0
            var rs = dbController.getConnection().createStatement().executeQuery("SELECT Cabinets_cabinet_id FROM Agents WHERE agent_id = $firstAgentId;")
            while (rs.next()) {
                firstCabinet = rs.getInt(1)
            }
            var secondCabinet = 0
            rs = dbController.getConnection().createStatement().executeQuery("SELECT Cabinets_cabinet_id FROM Agents WHERE agent_id = $secondAgentId;")
            while (rs.next()) {
                secondCabinet = rs.getInt(1)
            }

            if (firstCabinet == 0 || secondCabinet == 0) throw SQLException("Нет агентов с таким ID!")


            dbController.getConnection().setAutoCommit(false)

            val statementFirst = dbController.getConnection().prepareStatement("UPDATE Agents SET Agents.Cabinets_cabinet_id = ? WHERE Agents.agent_id = ?;\n")

            statementFirst.setInt(1, firstCabinet)
            statementFirst.setInt(2, secondAgentId)
            statementFirst.executeUpdate()// данные не обновлены!

            val statementSecond = dbController.getConnection().prepareStatement("UPDATE Agents SET Agents.Cabinets_cabinet_id = ? WHERE Agents.agent_id = ?;")
            statementSecond.setInt(1, secondCabinet)
            statementSecond.setInt(2, firstAgentId)
            statementSecond.executeUpdate()

            dbController.getConnection().commit()

            mailController.sendLetterOnCabinetChange(getEmail(firstAgentId), secondCabinet, getEmail(secondAgentId), firstCabinet)



        }
        catch(e: MessagingException) {
            JOptionPane.showMessageDialog(null, e.message, "Ошибка!", JOptionPane.ERROR_MESSAGE)
        }
        catch(e: SQLException) {
            JOptionPane.showMessageDialog(null, e.message, "Ошибка!", JOptionPane.ERROR_MESSAGE)
        }
    }

    fun getEmail(agentId: Int): String {
        var email = ""
        val rs = dbController.connection.createStatement().executeQuery(
                "SELECT email FROM Agents WHERE agent_id = " + agentId.toString() + ";")
        while (rs.next()) {
            email = rs.getString(1)
        }

        return email
    }

}