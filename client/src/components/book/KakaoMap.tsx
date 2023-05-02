/* eslint-disable react/button-has-type */
import React from 'react';
import styled from 'styled-components';
import { useEffect } from 'react';
import { FlexContainer } from 'pages/review/TeacherList/ReviewPage';

interface MapProps {
  latitude: number;
  longitude: number;
  keyword: string;
}

function KakaoMap({ latitude, longitude, keyword }: MapProps) {
  useEffect(() => {
    const mapScript = document.createElement('script');

    mapScript.async = true;
    mapScript.src = `//dapi.kakao.com/v2/maps/sdk.js?appkey=${process.env.REACT_APP_MAP_KEY}&autoload=false&libraries=services`;

    document.head.appendChild(mapScript);

    const onLoadKakaoMap = () => {
      window.kakao.maps.load(() => {
        const infowindow = new window.kakao.maps.InfoWindow({ zIndex: 1 });
        // 맵 설정
        const container = document.getElementById('map');
        const options = {
          center: new window.kakao.maps.LatLng(latitude, longitude),
          level: 3,
        };
        const map = new window.kakao.maps.Map(container, options);
        // 마커 설정
        const markerPosition = new window.kakao.maps.LatLng(
          latitude,
          longitude,
        );
        const marker = new window.kakao.maps.Marker({
          position: markerPosition,
        });
        marker.setMap(map);
        // 장소 검색
        const ps = new window.kakao.maps.services.Places();
        ps.keywordSearch(keyword, placesSearchCB);

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
      });
    };
    mapScript.addEventListener('load', onLoadKakaoMap);

    return () => mapScript.removeEventListener('load', onLoadKakaoMap);
  }, [latitude, longitude, keyword]);

  return (
    <FlexContainer dir="col">
      <MapContainer id="map" />
    </FlexContainer>
  );
}

const MapContainer = styled.div`
  aspect-ratio: 320 / 220;
  width: 700px;
  height: 600px;
`;

export default KakaoMap;
