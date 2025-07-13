<%@page import="DTO.CityDTO"%>
<%@page import="DAO.CityDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	//1. 브라우저가 보낸 요청(URL)에서 사용자가 선택, 입력한 값을 파라미터로 꺼내기
    // 사용자가 어떤 미션을 선택했는지? -> 밑의 <select> 옵션에서 받을 예정
    String option = request.getParameter("option");
	//사용자가 입력한 검색어가 뭔지?
    String keyword = request.getParameter("keyword");

    //2. DAO가 가져온 데이터(DTO)를 사용자에게 보여줄 최종 '문자열' 형태로 변환하여 담을 리스트
    List<String> resultStrings = new ArrayList<>();

    //3. 옵션 선택시에만 DB 작업을 수행
    if (option != null) {
        switch (option) {
        	//option의 value가 1일때
            case "1": { // 인구수보다 많은 도시
                CityDAO dao = new CityDAO();
               	try {
               		//DAO에 전달할 인구수 변수를 선언
               	 	Integer population = null; //키워드 입력이 되지 않은 경우 -> 전체 조회
               	 	
               	 	//키워드 입력이 된 경우
               		if (keyword != null && !keyword.isEmpty()) {
               			population = Integer.parseInt(keyword);
               		}
               	 	
               	 	//DAO 에서 method1 호출
               	 	List<CityDTO> cities = dao.method1(population);
                    
               	 	//4. DTO 리스트덩어리를 String 리스트로 변환해서 resultStrings에 담음
                    for (CityDTO city : cities) {
                    	resultStrings.add(String.format("%s, %,d", city.getName(), city.getPopulation()));
                    }
               	}catch (Exception e) { //숫자가 아닌 값을 입력했을 때등의 오류 발생
                	e.printStackTrace();
                }
                dao.close();
                break;
        	}//case 1종료 
            
      		//여기에 case 추가 

        }
    }

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB 미션</title>
</head>
<body>
    <h1>DB 미션 수행</h1>

    <!-- UI Part: 미션 선택 및 입력 폼 -->
    <div class="mission-box">
        <h3>미션 선택 및 실행</h3>
        <form action="dbmission.jsp" method="get">
            <select name="option">
                <option value="1">1. 인구수보다 많은 도시</option>
                <option value="2">2. 국가 코드로 도시 검색</option>
                <option value="3">3. 대륙으로 국가 검색</option>
            </select>
            <input type="text" name="keyword" placeholder="검색어 입력">
            <input type="submit" value="실행">
        </form>
    </div>

    <hr>

    <!-- View Part: 결과를 가장 간단한 형태로 출력 -->
    <div class="result-box">
        <%
        if (!resultStrings.isEmpty()) {
            // 단순하게 String 리스트를 순회하며 한 줄씩 출력
            for (String line : resultStrings) {
                out.println(line + "<br/>");
            }
        } else if (option != null) {
            out.println("조회된 결과가 없습니다.");
        }
        %>
    </div>
</body>
</html>