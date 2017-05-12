import string
import random

class RandomStringUtils:
	@staticmethod
	def random(count):
		pass

	@staticmethod
	def randomAscii(count):
		asscii = string.printable
		s = ''
		for i in range(0, count):
			s = s+random.choice(asscii)
		return s

	@staticmethod
	def randomAlphabetic(count):
		ascii_letters = string.ascii_letters
		s = ''
		for i in range(0, count):
			s = s+random.choice(ascii_letters)
		return s

	@staticmethod
	def randomAlphanumeric(count):
		alphanumeric = string.ascii_letters+string.digits
		s = ''
		for i in range(0, count):
			s += random.choice(alphanumeric)
		return s

	@staticmethod
	def randomNumeric(count):
		digits = string.digits
		s = ''
		for i in range(0, count):
			s = s+random.choice(digits)
		return s
