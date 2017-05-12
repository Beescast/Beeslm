from randomStringUtils import RandomStringUtils
import string
import random

class StringUtil:
	@staticmethod
	def code(lenth):
		codeSequence = string.uppercase + string.digits
		s = ''
		for i in range(0, lenth):
			s = s+random.choice(codeSequence)
		return RandomStringUtils.randomAlphanumeric(lenth)