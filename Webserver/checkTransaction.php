<?php

    $json = file_get_contents('php://input');
    $result = json_decode($json);
    
    // Set your merchant code (Note: Server key for sandbox and production mode are different)
    $merchantCode = "YOUR MERCHANT CODE"; 
    // Set your merchant key (Note: Server key for sandbox and production mode are different)
    $merchantKey = "YOUR MERCHANT KEY";
    
    $reference = $result->{'reference'}; 
    $signature = md5($merchantCode . $reference . $merchantKey);

    $itemsParam = array(
        'merchantCode' => $merchantCode,
        'signature' => $signature
    );
    
    class emp{}
    
    $params = array_merge((array)$result,$itemsParam);
    $params_string = json_encode($params);
    
    //if sandbox
    $url = 'http://sandbox.duitku.com/webapi/api/merchant/transactionStatus';
    //if production
    //$url = 'https://passport.duitku.com/webapi/api/merchant/transactionStatus';
    
    $ch = curl_init();

    curl_setopt($ch, CURLOPT_URL, $url); 
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");                                                                     
    curl_setopt($ch, CURLOPT_POSTFIELDS, $params_string);                                                                  
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);                                                                      
    curl_setopt($ch, CURLOPT_HTTPHEADER, array(                                                                          
        'Content-Type: application/json',                                                                                
        'Content-Length: ' . strlen($params_string))                                                                       
    );   
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);

    //execute post
    $request = curl_exec($ch);
    $httpCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);

    if($httpCode == 200)
    {
	      echo $request;
    }
    else
        echo $httpCode;
?>

