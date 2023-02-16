package com.rimichoi.airplane;

import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AirplaneApplicationTests {

	@Test
	void contextLoads() {
		Unirest.config().connectTimeout(20_000).socketTimeout(20_000);
		String price = Unirest.post("http://mair.gmarket.co.kr/mgm/init/srp/srpResultInitList.do")
				.header("Referer", "http://mair.gmarket.co.kr/mgm/init/srp/srpResultView.do?TTYPE=global&RTYPE=fromkr&SECTN=MD&DSTAD1=SEL&ASTAD1=LAX&DDATE1=20230702&DSTAD2=LAX&ASTAD2=CUN&DDATE2=20230707&DSTAD3=CUN&ASTAD3=SEL&DDATE3=20230713&DSIZE=3&NADT=2&NCHD=0&NINF=0&CLS=Y&VIAYN=false&MSITE=M")
				.header("Content-Type", "application/x-www-form-urlencoded")
				.header("Cookie", "WMONID=RbYFEInU3v8")
				.field("json_search", "{\"TTYPE\":\"global\",\"RTYPE\":\"fromkr\",\"SECTN\":\"MD\",\"NADT\":\"2\",\"NCHD\":\"0\",\"NINF\":\"0\",\"CLS\":\"Y\",\"DCLS\":\"일반석\",\"VIAYN\":false,\"DSTAD\":\"SEL\",\"DDESC\":\"인천,김포\",\"DEPNT\":\"KR\",\"DEPCT\":\"SEL\",\"DSTAD1\":\"SEL\",\"DDESC1\":\"인천,김포\",\"DEPNT1\":\"KR\",\"DEPCT1\":\"SEL\",\"DSIZE\":\"3\",\"DSTAD2\":\"LAX\",\"DDESC2\":\"로스앤젤레스\",\"DEPNT2\":\"US\",\"DEPCT2\":\"LAX\",\"ASTAD1\":\"LAX\",\"ADESC1\":\"로스앤젤레스\",\"ARRNT1\":\"US\",\"ARRCT1\":\"LAX\",\"ASTAD2\":\"CUN\",\"ADESC2\":\"칸쿤\",\"ARRNT2\":\"MX\",\"ARRCT2\":\"CUN\",\"DDATE1\":\"20230702\",\"DDATE2\":\"20230707\",\"DSTAD3\":\"CUN\",\"DDESC3\":\"칸쿤\",\"DEPNT3\":\"MX\",\"DEPCT3\":\"CUN\",\"ASTAD3\":\"SEL\",\"ADESC3\":\"인천,김포\",\"ARRNT3\":\"KR\",\"ARRCT3\":\"SEL\",\"DDATE3\":\"20230713\",\"MSITE\":\"M\"}")
				.field("sort_key", "HNSASC^DSFASC")
				.field("filter_data", "{}")
				.field("WAIT_ID", "")
				.field("PMT", "")
				.asJson()
				.getBody().getObject().getJSONArray("list").getJSONObject(0).getString("DSF");
		System.out.println(price);
	}

}
