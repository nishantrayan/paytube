package com.rayan.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.rayan.shared.PaytubeTransaction;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("paytube")
public interface PaytubeTransactionService extends RemoteService {
  String[] getPersons() throws IllegalArgumentException;
  boolean submitTransaction(PaytubeTransaction transaction);
}
