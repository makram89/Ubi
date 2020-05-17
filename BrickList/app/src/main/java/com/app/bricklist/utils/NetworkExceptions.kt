package com.app.bricklist.utils

class NetworkDisabledException :Exception("Network disabled. Turn it on and try again.")

class NoConnectionException : Exception("Cannot connect with database. Check your internet and try again. ")