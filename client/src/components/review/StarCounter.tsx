/* eslint-disable no-nested-ternary */
/* eslint-disable react/no-array-index-key */
import styled from 'styled-components';
import { AiFillStar } from 'react-icons/ai';

const RatingContainer = styled.div`
  display: flex;
  text-align: center;
  margin: 13px 0px;
  .inactive {
    color: gray;
  }
  .active {
    color: coral;
  }
`;

// AiFillStar는 react-icon 인데요 이거를 하트나 동그라미등 원하는거로 커스텀 가능합니다.
const RatingStar = styled(AiFillStar)`
  cursor: pointer;
`;

type RatingSectionProps = {
  starPoint: number;
  setStarPoint: React.Dispatch<React.SetStateAction<number>>;
};

function StarCounter({ starPoint, setStarPoint }: RatingSectionProps) {
  // map를 사용하려고 급조한 array 입니다;;
  const ArrayIndexes = [1, 2, 3, 4, 5];
  return (
    <RatingContainer>
      {ArrayIndexes.map((arrayindex, index) => (
        <RatingStar
          size={35}
          key={`rating_${index}`}
          // 여기가 핵심
          className={arrayindex <= starPoint ? 'active' : 'inactive'}
          onClick={() => setStarPoint(arrayindex)}
        />
      ))}
    </RatingContainer>
  );
}

export default StarCounter;
