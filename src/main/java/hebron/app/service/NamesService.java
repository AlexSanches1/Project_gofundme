package hebron.app.service;

import hebron.app.repositry.NamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NamesService {

    private NamesRepository namesRepository;

    private ConvertService convertService;

    @Autowired
    public NamesService(NamesRepository namesRepository, ConvertService convertService) {
        this.namesRepository = namesRepository;
        this.convertService = convertService;
    }

    public String getMD5NameByOriginalName(String name) {
        return namesRepository.findByName(name).get(0).getMd5();
    }

    public String getMd5NameOfFileByBytes(byte[] bytes) {
        return convertService.convertFileToMD5(bytes);
    }
}

