import re
import datetime

class ValidateUtils:
	@staticmethod
	def isInt(arg):
		if type(arg) == int:
			return True
		else:
			return False

	@staticmethod
	def isFloat(arg):
		if type(arg) == float:
			return True
		else:
			return False

	@staticmethod
	def isLong(arg):
		if type(arg) == long:
			return True
		else:
			return False

	#@staticmethod
	#def isDouble(arg):

	@staticmethod
	def isDateTime(arg):
		if type(arg) == datetime.datetime:
			return True
		else:
			return False

	@staticmethod
	def isNull(value):
		if type(value) == type(None):
			return True
		elif value == '' or value == 'null':
			return True
		else:
			return False

	@staticmethod
	def notNull(value):
		return not ValidateUtils.isNull(value)

	@staticmethod
	def exceedLength(value, length):
		return ValidateUtils.isNull(value) or len(value) > length

	@staticmethod
	def lessLength(value, length):
		return ValidateUtils.isNull(value) or len(value) < length

	@staticmethod
	def containHTML(value):
		return value.find("<") >= 0 or value.find(">") >= 0 or value.find("&") >= 0 or value.find("\"") >= 0 or value.find("'") >= 0 or value.find("\\") >= 0

	@staticmethod
	def contains(array, value):
		if array:
			for s in array:
				if s == value:
					return True
		return False

	@staticmethod
	def isEmail(email):
		if ValidateUtils.isNull(email):
			return False
		if not ValidateUtils.allValidChars(email):
			return False
		if email.find('@') < 1:
			return False
		if email.rfind('.') <= email.find('@'):
			return False
		if email.find('@') == len(email):
			return False
		if email.find('..') >= 0:
			return
		return email.find('.') != len(email)

	@staticmethod
	def isMobile(mobile):
		if ValidateUtils.isNull(mobile):
			return False
		if re.search('^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|6|7|8]|18[0-9])\\d{8}$', mobile):
			return True
		else:
			return False

	@staticmethod
	def isIdNumber(idNumber):
		if ValidateUtils.isNull(idNumber):
			return False
		if re.search('^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$', idNumber):
			return True
		else:
			return False

	@staticmethod
	def isIDCard(value, provinceCode, birthday):
		return False

	@staticmethod
	def allValidChars(c):
		s = c.lower()
		parsed = True
		validchars = 'abcdefghijklmnopqrstuvwxyz0123456789@.-_'
		for i in range(0, len(s)):
			letter = s[i]
			if validchars.find(letter) != -1:
				continue
			parsed = False
			break
		return parsed
