package com.automationpractice.helper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.automationpractice.models.Address;
import com.automationpractice.models.CreateAccount;
import com.google.gson.Gson;

public class JsonReader {
    private final String createAccountPath = System.getProperty("user.dir")+"/src/test/resources/data/createAccount.json";
    private final String addressPath = System.getProperty("user.dir")+"/src/test/resources/data/address.json";
	private List<CreateAccount> createAccountList;
	private List<Address> addressList;
	private Gson gson = new Gson();
    private BufferedReader bufferedReader = null;

    public JsonReader() { 
        createAccountList = getBasicCreateAccountData();
        addressList = getAddressData();
    }

    private List<CreateAccount> getBasicCreateAccountData() {
        try {
            bufferedReader = new BufferedReader(new FileReader(createAccountPath));
            CreateAccount[] leads = gson.fromJson(bufferedReader, CreateAccount[].class);
            return Arrays.asList(leads);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Create Account date file not found at path:" + createAccountPath);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
            }
        }
    }

    private List<Address> getAddressData() {
        try {
            bufferedReader = new BufferedReader(new FileReader(addressPath));
            Address[] leads = gson.fromJson(bufferedReader, Address[].class);
            return Arrays.asList(leads);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Address date file not found at path:" + addressPath);
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
            }
        }
    }
    
    public final CreateAccount getpageByFirstName(String name) {
        return createAccountList.stream().filter(c -> c.createNewAccount.firstName.equalsIgnoreCase(name)).findAny().get();
    }
    
    public final Address getAddressByReference(String addressReference) {
        return addressList.stream().filter(c -> c.newAddress.addressReference.equalsIgnoreCase(addressReference)).findAny().get();
    }
}
