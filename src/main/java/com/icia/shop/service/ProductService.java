package com.icia.shop.service;

import com.icia.shop.dto.ProductDTO;
import com.icia.shop.dto.ProductFileDTO;
import com.icia.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public void save(ProductDTO productDTO) throws IOException {
        if (productDTO.getProductFile().get(0).isEmpty()) {
            System.out.println("파일없음");
            productDTO.setFileAttached(0);
            productRepository.save(productDTO);
        } else {
            //파일 있음
            /*
            <흐름>
            * 1. 파일 첨부 여부 값 1로 세팅하고 DB에 제목 등 내용 board_table에 저장 처리
            * 2. 파일의 이름을 가져오고 DTO 값에 세팅
            * 3. 저장용 파일 이름을 만들고 DTO 필드값에 세팅
            * 4. 로컬에 파일 저장
            * 5. board_file_table에 저장 처리
            * */
            System.out.println("파일있음");
            productDTO.setFileAttached(1);
            //dto는 사용자가 입력한 인풋값을 담음(아이디가값이 담겨있음)
            ProductDTO dto = productRepository.save(productDTO);
            //파일이 여러개니까 반복문을 돌려야됌
            //여러개의 파일을 하나씩 처리해줘야됌
            for (MultipartFile productFile : productDTO.getProductFile()) {

                // 원본 파일 이름 가져오기
                String originalFilename = productFile.getOriginalFilename();
                System.out.println("originalFilename = " + originalFilename);

                // 저장용 이름 만들기

                String storedFileName = System.currentTimeMillis() + "-" + originalFilename;
                System.out.println("storedFileName = " + storedFileName);

                //저장을 위한 BoardFileDTO세팅
                ProductFileDTO productFileDTO = new ProductFileDTO();
                productFileDTO.setOriginalFileName(originalFilename);
                productFileDTO.setStoredFileName(storedFileName);
                productFileDTO.setProductId(dto.getId());

                //로컬에 파일 저장
                //저장할 경로 설정(저장할 폴더 + 저장할 이름)
                //어디(큰따옴표안에 쓴곳)에 어떤 이름(storedFileName)으로 저장할지 경로를 만들어줌
                // 큰따옴표 안에 젤 뒤에 백슬래시(\\) 두개 추가 필수 꼮꼮꼮꼬꼮ㄲ!!
                String savePath = "D:\\springframework_img\\" + storedFileName;

                //저장처리
                // 리스트에 담겨있는데 for문으로 접근하고있으므로 각각 첨부파일로 접근함
                productFile.transferTo(new File(savePath));

                //boardFileDTO여기에는 인풋에서 입력받아온 원본파일이름이랑, 저장파일이름, 아이디값이 들어있음
                productRepository.saveFile(productFileDTO);
            }
        }
    }
}