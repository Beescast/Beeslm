#import RuntimeError

class ServiceException(RuntimeError):
	def __init__(self, message, cause=None):
		self.message = message
		self.cause = cause

	def getMessage(self):
		return self.message
