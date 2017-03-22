package org.bees.utils;

import java.util.Random;


public class StringUtil{
	public static String code(int lenth){
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
			      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			      'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer randomCode = new StringBuffer();
	    // 创建一个随机数生成器类
	    Random random = new Random();
	    // 随机产生codeCount数字的验证码。
	    for (int i = 0; i < lenth; i++) {
	      // 得到随机产生的验证码数字。
	      String code = String.valueOf(codeSequence[random.nextInt(36)]);

	      // 将产生的四个随机数组合在一起。
	      randomCode.append(code);
	    }
	    return randomCode.toString();
	}

}

