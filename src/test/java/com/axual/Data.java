package com.axual;

import com.axual.model.FailedTransaction;
import com.axual.model.Record;
import com.axual.validator.TransactionValidation;

import java.util.List;
import java.util.Set;

public final class Data {
    private Data() {

    }

    public static List<Record> buildRecordsXML() {
        return List.of(
                Record.builder().trxReference(177190L).accountNumber("NL56RABO0149876948").description("Subscription from Rik Theuß").startBalance(72.36).mutation(+15.59).endBalance(87.95).build(),
                Record.builder().trxReference(180899L).accountNumber("NL91RABO0315273637").description("Subscription for Willem Dekker").startBalance(5429d).mutation(-939d).endBalance(6368d).build(),
                Record.builder().trxReference(161419L).accountNumber("NL56RABO0149876948").description("Candy for Daniël Kin").startBalance(29.87).mutation(+25.06).endBalance(54.93).build(),
                Record.builder().trxReference(177331L).accountNumber("NL74ABNA0248990274").description("Flowers from Daniël King").startBalance(20.37).mutation(-28.28).endBalance(-7.91).build(),
                Record.builder().trxReference(178741L).accountNumber("NL43AEGO0773393871").description("Tickets from Jan Dekker").startBalance(63.08).mutation(-43.09).endBalance(19.99).build(),
                Record.builder().trxReference(108742L).accountNumber("NL46ABNA0625805417").description("Candy for Daniël de Vries").startBalance(48.48).mutation(+46.68).endBalance(95.16).build(),
                Record.builder().trxReference(136529L).accountNumber("NL90ABNA0585647886").description("Clothes from Daniël Theuß").startBalance(10.1).mutation(-0.3).endBalance(9.8).build(),
                Record.builder().trxReference(118363L).accountNumber("NL56RABO0149876948").description("Subscription from Peter de Vries").startBalance(36.75).mutation(-23.47).endBalance(13.28).build(),
                Record.builder().trxReference(183892L).accountNumber("NL27SNSB0917829871").description("Subscription from Vincent Theuß").startBalance(3980d).mutation(+1000d).endBalance(4981d).build(),
                Record.builder().trxReference(188299L).accountNumber("NL46ABNA0625805417").description("Flowers for Jan Theuß").startBalance(32.39).mutation(+35.65).endBalance(68.04).build()
        );
    }

    public static List<Record> buildRecordsCSV() {
        return List.of(
                Record.builder().trxReference(190388L).accountNumber("NL93ABNA0585619023").description("Subscription from Dani�l Bakker").startBalance(82.99).mutation(+12.06).endBalance(95.05).build(),
                Record.builder().trxReference(112806L).accountNumber("NL56RABO0149876948").description("Tickets for Willem King").startBalance(90.49).mutation(+4.6).endBalance(95.09).build(),
                Record.builder().trxReference(116032L).accountNumber("NL91RABO0315273637").description("Flowers from Rik de Vries").startBalance(99.54).mutation(-24.14).endBalance(75.4).build(),
                Record.builder().trxReference(133252L).accountNumber("NL32RABO0195610843").description("Clothes from Richard King").startBalance(84.92).mutation(-1.96).endBalance(82.96).build(),
                Record.builder().trxReference(112806L).accountNumber("NL69ABNA0433647324").description("Subscription from Rik Dekker").startBalance(96.18).mutation(+15.87).endBalance(112.05).build(),
                Record.builder().trxReference(112806L).accountNumber("NL90ABNA0585647886").description("Clothes from Jan de Vries").startBalance(39.8).mutation(+26.41).endBalance(66.21).build(),
                Record.builder().trxReference(118402L).accountNumber("NL43AEGO0773393871").description("Flowers from Rik de Vries").startBalance(17.42).mutation(+32.47).endBalance(49.89).build(),
                Record.builder().trxReference(131279L).accountNumber("NL90ABNA0585647886").description("Candy from Dani�l de Vries").startBalance(90.44).mutation(-27.19).endBalance(63.25).build(),
                Record.builder().trxReference(164566L).accountNumber("NL74ABNA0248990274").description("Flowers from Richard Theu�").startBalance(57.21).mutation(+48.00).endBalance(105.21).build(),
                Record.builder().trxReference(110986L).accountNumber("NL69ABNA0433647324").description("Subscription from Dani�l de Vries").startBalance(80.97).mutation(+37.86).endBalance(118.83).build()
        );
    }

    public static Set<FailedTransaction> buildFailedCSV(){
        return Set.of(
          FailedTransaction.builder().reference(112806L).description(TransactionValidation.DUPLICATE_RECORD).build()
        );
    }

    public static Set<FailedTransaction> buildFailedXML(){
        return Set.of(
                FailedTransaction.builder().reference(180899L).description(TransactionValidation.INVALID_END_BALANCE).build(),
                FailedTransaction.builder().reference(183892L).description(TransactionValidation.INVALID_END_BALANCE).build()
        );
    }
}

