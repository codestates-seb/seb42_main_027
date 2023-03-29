import BaseButton from 'components/common/BaseButton';
import { useNavigate } from 'react-router';
import styled from 'styled-components';
import theme from 'theme';
import reviewMac from '../../assets/images/mac.png';

const { colors } = theme;

const Container = styled.div`
  width: 100%;
  padding-bottom: 5rem;
  display: flex;
  flex-direction: column;
  overflow: hidden;
  background-color: ${colors.pointColor};
`;

const ContentWrapper = styled.div`
  margin-top: 5rem;
  margin-left: 3rem;
  width: 48rem;
  height: 15rem;
`;

const Title = styled.div`
  font-size: 4rem;
  color: ${colors.white};
  margin-bottom: 1.5rem;
`;

const SubTitle = styled.div`
  font-size: 1.5rem;
  color: ${colors.white};
  margin-bottom: 1.5rem;
  & :first-child {
    margin-bottom: 0.3rem;
  }
`;

const BtnWrapper = styled.div`
  width: 10rem;
  margin: 1rem 0;
`;

const MacWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-end;
  padding-right: 2rem;
`;

function ReviewIntroduction() {
  const navigate = useNavigate();

  const handleClickReviewBtn = () => {
    navigate('/ReviewPage');
  };

  return (
    <Container>
      <ContentWrapper data-aos="fade-right">
        <Title>
          모든 인강 정보와
          <br /> 후기를 한 눈에
        </Title>
        <SubTitle>
          <p>모든 인강 정보와 후기를 한 눈에 볼 수 있는 서비스로,</p>
          <p>
            수강생들은 다양한 검색 조건으로 쉽게 인강 정보를 찾을 수 있습니다.
          </p>
        </SubTitle>
        <BtnWrapper>
          <BaseButton
            onClick={handleClickReviewBtn}
            color="white"
            size="md"
            disabled={false}
          >
            리뷰 둘러보기
          </BaseButton>
        </BtnWrapper>
      </ContentWrapper>

      <MacWrapper>
        <img alt="mac" src={reviewMac} />
      </MacWrapper>
    </Container>
  );
}
export default ReviewIntroduction;
