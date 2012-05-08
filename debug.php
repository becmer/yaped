<?php

class DebugClient {

	private static function exceptionToJSON($ex) {
		if (!($ex instanceof Exception)) return null;
		$o = array(
			'message' => $ex->getMessage(),
			'previous' => self::exceptionToJSON($ex->getPrevious()),
			'code' => $ex->getCode(),
			'file' => $ex->getFile(),
			'line' => $ex->getLine(),
			'trace' => $ex->getTrace()
		);
		return json_encode($o);
	}
	
	private $socket;
	private $host;
	private $port;
	
	public function __construct($host, $port) {
		$this->host = $host;
		$this->port = $port;
		$this->socket = fsockopen($host, $port, $errno, $errstr);
		if (!$this->socket) throw new Exception('Cannot open socket to '.$host.' at port '.$port);
	}
	
	public function __destruct() {
		fclose($this->socket);
	}
	
	public function send($ex) {
		if (!($ex instanceof Exception)) return false;
		try {
			$data = self::exceptionToJSON($ex);
			$head = "POST / HTTP/1.1\r\nContent-type: application/json\r\nHost: ".$this->host."\r\nContent-length: ".strlen($data)."\r\n\r\n";
			fputs($this->socket, $head);
			fputs($this->socket, $data);
			return true;
		} catch (Exception $inex) {
			return false;
		}
	}

}

function test(){
	throw new Exception('blahblah');
}

$dbg = new DebugClient('localhost',9001);

try {
	test('d',45);
} catch (Exception $ex) {
	$dbg->send($ex);
}

?>