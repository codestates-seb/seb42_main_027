/* eslint-disable @typescript-eslint/no-empty-function */
/* eslint-disable react/button-has-type */
import React from 'react';
import styled from 'styled-components';
import { useEffect, useRef } from 'react';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

interface MapProps {
  latitude: number;
  longitude: number;
  keyword: string;
}

function KakaoMap({ latitude, longitude, keyword }: MapProps) {
  const mapContainer = useRef<HTMLDivElement>(null);
  const options = {
    // 지도를 생성할 때 필요한 기본 옵션
    center: new window.kakao.maps.LatLng(latitude, longitude), // 지도의 중심좌표.
    level: 3, // 지도의 레벨(확대, 축소 정도)
    // draggable: false,
  };
  const ps = new window.kakao.maps.services.Places();
  const infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });

  useEffect(() => {
    const map = new window.kakao.maps.Map(mapContainer.current, options);
    // 맵 타입 설정. 스카이뷰
    // map.setMapTypeId(window.kakao.maps.MapTypeId.HYBRID);

    // 맵 타입 선택창 표시
    const mapTypeControl = new window.kakao.maps.MapTypeControl();
    map.addControl(mapTypeControl, window.kakao.maps.ControlPosition.TOPRIGHT);

    // 줌 컨트롤 표시
    const zoomControl = new window.kakao.maps.ZoomControl();
    map.addControl(zoomControl, window.kakao.maps.ControlPosition.RIGHT);

    ps.keywordSearch(keyword || '이태원 맛집', placesSearchCB);

    // 키워드 검색 완료 시 호출되는 콜백함수
    function placesSearchCB(data: any, status: any) {
      if (status === window.kakao.maps.services.Status.OK) {
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        const bounds = new window.kakao.maps.LatLngBounds();

        for (let i = 0; i < data.length; i += 1) {
          displayMarker(data[i]);
          bounds.extend(new window.kakao.maps.LatLng(data[i].y, data[i].x));
        }

        // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
        map.setBounds(bounds);
      }
    }

    // 지도에 마커를 표시하는 함수입니다
    function displayMarker(place: any) {
      // 마커를 생성하고 지도에 표시합니다
      const marker = new window.kakao.maps.Marker({
        map,
        position: new window.kakao.maps.LatLng(place.y, place.x),
      });

      // 마커에 클릭이벤트를 등록합니다
      window.kakao.maps.event.addListener(marker, 'click', function () {
        // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
        infowindow.setContent(
          `<div style="padding:5px;font-size:12px;">${place.place_name}</div>`,
        );
        infowindow.open(map, marker);
      });
    }

    return () => {};
  }, [keyword]);

  return (
    <FlexContainer dir="col">
      <MapContainer ref={mapContainer} />
    </FlexContainer>
  );
}

const MapContainer = styled.div`
  aspect-ratio: 320 / 220;
  width: 700px;
  height: 600px;
`;

export default KakaoMap;
