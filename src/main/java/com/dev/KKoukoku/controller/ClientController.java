package com.dev.KKoukoku.controller;

import org.apache.commons.codec.EncoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.KKoukoku.model.Client;
import com.dev.KKoukoku.service.ClientService;
import com.dev.KKoukoku.service.SmsService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	ClientService clientService;
	
	@Autowired
	SmsService smsService;
	
	@PostMapping("/japanInsert")
	@ResponseBody
	public String japanInsert(
			Client client,
			Model model
			) throws EncoderException {
		
		smsService.sendMessage("010-7508-3197", "KKoukoku 문의 왔습니다.");
		String msg = "";
		if(clientService.japanClientInsert(client)) {
			msg = "申し込み頂きありがとうこざいます。担当のものが確認後、折り返しご連絡させて頂きます。";
		}else {
			msg = "エラーが発生しました。LINEにてお問い合わせお願いいたします。";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("alert('"+msg+"');");
		sb.append("location.href='/'");
		sb.insert(0, "<script>");
		sb.append("</script>");
		return sb.toString();
	}
}

