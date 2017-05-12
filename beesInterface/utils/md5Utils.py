import hashlib
import binascii

class MD5Utils:
	@staticmethod
	def getMD5String(s):
		return hashlib.md5(s).hexdigest()