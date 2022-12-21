package com.example.ordersservice.service;

import com.example.ordersservice.pojo.Order;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

     public String messagePayment(Order order){
         String food = order.getFoodMenu()!=null?"ชุดเครื่องดื่ม: "+order.getFoodMenu().getName()+"   ราคา: "+order.getFoodMenu().getPrice():"ชุดเครื่องดื่ม: ไม่รับ";
         String drink = order.getDrinkMenu()!=null?"ชุดเครื่องดื่ม: "+order.getFoodMenu().getName()+"   ราคา: "+order.getFoodMenu().getPrice():"ชุดเครื่องดื่ม: ไม่รับ";
         return  "           แจ้งยอดชำระ         "+"\n\n"+
                 "เลขอ้างอิงคำสั่งซื้อ: "+order.get_id()+"\n\n"+
                 "ลูกค้า: "+order.getName()+"\n\n"+
                 "ห้อง: "+order.getRoom().getName()+", ชุดเครื่องเสียง: "+order.getRoom().getAccessory()+", เวลาที่จอง: "+order.getTime()+", ไมค์: "+order.getMicrophone()+"   ราคา: "+order.getRoom().getPrice()+"\n\n"+
                 food +"\n\n"+
                 drink +"\n\n"+
                 "รวมยอด: "+order.getResult()+"\n\n\n\n"+
                 "ชำระได้ที่เลขบัญชี: XXXXXXXXXXXXXXXX นาย XXXXX XXXXX"+"\n\n"+
                 "แจ้งโอนที่ได้ http://localhost:8080/Paymentpage"
                 ;
     }

     public String messageAddOrder(Order order){
         return "               ขอบคุณที่มาใช้บริการ SOP KARAOKE             "+"\n\n\n"+
                 "เลขอ้างอิงคำสั่งซื้อ: "+order.get_id()+"\n\n\n"+
                 "กรุณารอการตรวจสอบประมาณ 1-3 ชั่วโมง"+"\n\n"+
                 "หากไม่มีการตอบรับสามารถติดต่อได้ที่ email: sop.karaoke@gmail.com เบอร์ติดต่อ: 080-xxx-xxxx"
                 ;
    }

    public String messageCancelOrder(Order order){
        return "               คำสั่งซื้อ SOP KARAOKE ของคุณถูกยกเลิกขออภัยในความไม่สะดวก             "+"\n\n\n"+
                "เลขอ้างอิงคำสั่งซื้อ: "+order.get_id()+"\n\n\n"+
                "ลูกค้าสามารถติดต่อสอบถามได้ที่ email: sop.karaoke@gmail.com เบอร์ติดต่อ: 080-xxx-xxxx"+"\n\n"
                ;
    }

}
