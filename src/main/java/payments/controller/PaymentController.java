package payments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import payments.model.Payment;
import payments.service.PaymentService;

import java.util.List;

@Controller
public class PaymentController {

    private PaymentService paymentService;

    @Autowired(required = true)
    @Qualifier(value = "paymentService")
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }


    public List<Payment> getListPaymentsNotSend(){
        return this.paymentService.listPayments(0);

    }

    public void updatePayment(Payment payment){
        this.paymentService.updatePayment(payment);

    }

    public void addPayment(Payment payment){
        this.paymentService.addPayment(payment);

    }



}
