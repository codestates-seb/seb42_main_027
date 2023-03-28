import BaseButton from 'components/common/BaseButton';
import { useNavigate } from 'react-router';
import styled from 'styled-components';
import theme from 'theme';
import reviewMac from '../../assets/images/Group 72.png';

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
  height: 20rem;
`;

const Title = styled.div`
  font-size: 4rem;
  color: ${colors.white};
`;

const SubTitle = styled.div`
  font-size: 1.5rem;
  color: ${colors.white};
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
          국내 모든 인강 정보와
          <br /> 후기를 한 눈에
        </Title>
        <SubTitle>
          Lorem ipsum dolor sit, amet consectetur adipisicing elit. Corporis at
          repudiandae ducimus distinctio fuga iste, labore animi perspiciatis,
          accusantium illo laudantium provident expedita vero molestiae saepe.
          Voluptatem eveniet unde quaerat.
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
