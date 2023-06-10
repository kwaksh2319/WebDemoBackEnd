package kr.co.kshproject.webDemo.Applicaiton;

import kr.co.kshproject.webDemo.Domain.IpAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpAddressService implements IpAddressServiceInterface {
    @Autowired
    private IpAddressDao ipAddressDao;
    public void save(String ipAddress) {

        ipAddressDao.save(ipAddress);
    }

}
