package com.icia.shop.service;

import com.icia.shop.dto.PageDTO;
import com.icia.shop.dto.ProductDTO;
import com.icia.shop.dto.ProductFileDTO;
import com.icia.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    public List<ProductDTO> findAll() {
        return productRepository.findAll();
    }

    public List<ProductDTO> pagingList(int page) {
        int pageLimit = 5; // 한페이지에 보여줄 글 목록 갯수
        int pagingStart = (page-1) * pageLimit; //사용자가 보고싶은 페이지의 게시글 시작 번호(?)
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("Limit", pageLimit);
        List<ProductDTO> productDTOList = productRepository.pagingList(pagingParams);
        return productDTOList;
    }

    public PageDTO pagingParam(int page) {
        int pageLimit = 5; //한 페이지에 보여줄 글 갯수
        int blockLimit = 3; //하단에 보여줄 페이지 번호 갯수
        // 전체 글 갯수 조회
        int productCount = productRepository.boardCount();

        // 전체 페이지 갯수 계산
        //전체 글게수를 실수로 바꿔서 3의로 나눈 뒤 올림처리를 한 후 정수로 형변환을 해서 maxPage에 담는다
        int maxPage = (int)(Math.ceil((double)productCount / pageLimit)); // 정수/정수 = 정수(소수는 버림)-->올림처리를 해서 소수점도 가져가야됨

        //시작 페이지 값 계산(1,4,7,10~~`~`)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 마지막 페이지 값 계산(3,6,9,12~~)
        int endPage = startPage + blockLimit -1;

        // 전체 페이지 갯수가 계산한 endPage보다 작을 때는 endPAge값을 maxPage 값과 같게 세팅
        // 맥스랑 앤드를 비교해서 필요없으면 맥스를 앤드로
        //13게시글에서 엔드를 6으로 했는데 맥스는 5일때 6은 필요없으니까 맥스값을 넣어서 앤드를 5로 해줌
        if(endPage > maxPage){
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);
        return pageDTO;


    }

    public void updateHits(Long id) {
        productRepository.updateHits(id);
    }

    public ProductDTO findById(Long id) {
        System.out.println(" 여긴 서비스 id = " + id);
        return productRepository.findById(id);
    }

    public List<ProductFileDTO> findFile(Long id) {
        return productRepository.findFile(id);
    }

    public void update(ProductDTO productDTO) {
        productRepository.update(productDTO);
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }

    public List<ProductDTO> searchList(int page, String type, String q) {
        int pageLimit = 5; // 한페이지에 보여줄 글 갯수
        int pagingStart = (page-1) * pageLimit;
        // 검색어는 string  나머지는 int를 다 map에서 받아야되서 셋다 뭉쳐서 object형으로 씀
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        pagingParams.put("q", q);
        pagingParams.put("type", type);
        List<ProductDTO> productDTOList = productRepository.searchList(pagingParams);
        return productDTOList;
    }

    public PageDTO pagingSearchParam(int page, String type, String q) {
        int pageLimit = 5; // 한페이지에 보여줄 글 갯수
        int blockLimit = 3; // 하단에 보여줄 페이지 번호 갯수
        Map<String, Object> pagingParams = new HashMap<>();
        pagingParams.put("q", q);
        pagingParams.put("type", type);
        // 전체 글 갯수 조회
        // 검색어가 포함된 게시글 갯수를 알아야됌
        int productCount = productRepository.productSearchCount(pagingParams);
        // 전체 페이지 갯수 계산
        int maxPage = (int) (Math.ceil((double)productCount / pageLimit));
        // 시작 페이지 값 계산(1, 4, 7, 10 ~~)
        int startPage = (((int)(Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;
        // 마지막 페이지 값 계산(3, 6, 9, 12 ~~)
        int endPage = startPage + blockLimit - 1;
        // 전체 페이지 갯수가 계산한 endPage 보다 작을 때는 endPage 값을 maxPage 값과 같게 세팅
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);
        return pageDTO;

    }
}