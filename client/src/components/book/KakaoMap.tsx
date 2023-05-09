/* eslint-disable no-unused-expressions */
/* eslint-disable consistent-return */
/* eslint-disable @typescript-eslint/no-empty-function */
/* eslint-disable react/button-has-type */
import React, { useState } from 'react';
import styled from 'styled-components';
import { useEffect, useRef } from 'react';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

interface MapProps {
  latitude: number;
  longitude: number;
  keyword: string;
}

function KakaoMap({ latitude, longitude, keyword }: MapProps) {
  let markers: any[] = [];
  const [map, setMap] = useState<any>(null);
  const mapContainer = useRef<HTMLDivElement>(null);
  const options = {
    // 지도를 생성할 때 필요한 기본 옵션
    center: new window.kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표.
    level: 3, // 지도의 레벨(확대, 축소 정도)
    // draggable: false,
  };
  const ps = new window.kakao.maps.services.Places();

  // 초기 카카오맵 세팅 Hook.
  useEffect(() => {
    const myMap = new window.kakao.maps.Map(mapContainer.current, options);

    // 맵 타입 설정. 스카이뷰
    // myMap.setMapTypeId(window.kakao.maps.MapTypeId.HYBRID);

    // 맵 타입 선택창 표시
    const mapTypeControl = new window.kakao.maps.MapTypeControl();
    myMap.addControl(
      mapTypeControl,
      window.kakao.maps.ControlPosition.TOPRIGHT,
    );

    // 줌 컨트롤 표시
    const zoomControl = new window.kakao.maps.ZoomControl();
    myMap.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);

    setMap(myMap);
    setMap(myMap);
  }, []);

  // 카카오맵 검색 Hook
  useEffect(() => {
    // map이 없으면 실행 안함
    if (map === null) return;

    searchPlaces();

    function searchPlaces() {
      if (!keyword.replace(/^\s+|\s+$/g, '')) {
        alert('키워드를 입력해주세요!');
        return false;
      }

      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      ps.keywordSearch(keyword, placesSearchCB);
    }

    function placesSearchCB(data: any, status: any) {
      if (status === window.kakao.maps.services.Status.OK) {
        // 정상적으로 검색이 완료됐으면
        // 검색 목록과 마커를 표출합니다
        displayPlaces(data);
      } else if (status === window.kakao.maps.services.Status.ZERO_RESULT) {
        alert('검색 결과가 존재하지 않습니다.');
      } else if (status === window.kakao.maps.services.Status.ERROR) {
        alert('검색 결과 중 오류가 발생했습니다.');
      }
    }

    function displayPlaces(places: any) {
      const bounds = new window.kakao.maps.LatLngBounds();

      // 지도에 표시되고 있는 마커를 제거합니다
      removeMarker();

      for (let i = 0; i < places.length; i += 1) {
        // 마커를 생성하고 지도에 표시합니다
        const placePosition = new window.kakao.maps.LatLng(
          places[i].y,
          places[i].x,
        );
        const marker = addMarker(placePosition, i);
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다

      if (map !== null) map.setBounds(bounds);
    }

    function addMarker(position: any, idx: any) {
      const imageSrc =
        'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png'; // 마커 이미지 url, 스프라이트 이미지를 씁니다
      const imageSize = new window.kakao.maps.Size(36, 37); // 마커 이미지의 크기
      const imgOptions = {
        spriteSize: new window.kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
        spriteOrigin: new window.kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
        offset: new window.kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
      };
      const markerImage = new window.kakao.maps.MarkerImage(
        imageSrc,
        imageSize,
        imgOptions,
      );
      const marker = new window.kakao.maps.Marker({
        position, // 마커의 위치
        image: markerImage,
      });

      marker.setMap(map); // 지도 위에 마커를 표출합니다
      markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    }

    function removeMarker() {
      for (let i = 0; i < markers.length; i += 1) {
        markers[i].setMap(null);
      }
      markers = [];
    }
  }, [keyword]);

  return (
    <FlexContainer dir="col">
      <MapContainer id="map" ref={mapContainer} />
    </FlexContainer>
  );
}

const MapContainer = styled.div`
  aspect-ratio: 320 / 220;
  width: 700px;
  height: 600px;
`;

export default KakaoMap;
