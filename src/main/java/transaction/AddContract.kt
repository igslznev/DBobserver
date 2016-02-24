package transaction


import controller.DBController
import controller.MailController
import sun.nio.ch.sctp.SendFailed
import java.sql.SQLException
import java.time.LocalDate
import javax.mail.SendFailedException
import javax.swing.JOptionPane

class AddContract constructor(val dbController: DBController) {

    companion object {
        val mailController = MailController()
    }


    fun addContractTransaction(price: String, startDate: LocalDate, expDate: LocalDate, clientId: String, agentId: String, type: String) {
        try {
            dbController.connection.autoCommit = false
            val statement = dbController.connection.prepareStatement(
                    "INSERT INTO Contracts (isOccured, price, prize, startDate, expDate, Clients_client_id, Agents_agent_id, type)\nVALUES (0, ?, 1.2 * ?, ?, ?, ?, ?, ?);")
            statement.setInt(1, Integer.parseInt(price))
            statement.setInt(2, Integer.parseInt(price))
            //String startDateString = "\"" + startDate.toString() + "\"";
            statement.setString(3, startDate.toString())
            //String expDateString = "\"" + expDate.toString() + "\"";
            statement.setString(4, expDate.toString())
            statement.setInt(5, Integer.parseInt(clientId))
            statement.setInt(6, Integer.parseInt(agentId))
            //String typeString = "\"" + type + "\"";
            statement.setString(7, type)

            statement.executeUpdate()

            dbController.connection.commit()
            mailController.sendLetterOnAddContract(price, startDate, expDate, getClient(Integer.parseInt(clientId)),
                    getEmail(Integer.parseInt(agentId)), type)
        }
        catch (e: SendFailedException) {
            JOptionPane.showMessageDialog(null, e.message, "Ошибка!", JOptionPane.ERROR_MESSAGE)
        }
        catch (e: SQLException) {
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

    fun getClient(clientId: Int): String {
        var name = ""
        val rs = dbController.connection.createStatement().executeQuery(
                "SELECT name FROM Clients WHERE client_id = " + clientId.toString() + ";")
        while (rs.next()) {
            name = rs.getString(1)
        }

        return name
    }
}