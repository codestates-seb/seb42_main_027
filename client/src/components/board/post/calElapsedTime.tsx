function CalElapsedTime(time: string) {
  let calTime = time.slice(0, 24);
  const startTime = new Date(time).getTime();
  const endTime = Date.now();
  let elapsedTime = (endTime - startTime) / 1000;

  if (elapsedTime < 60) {
    calTime = '방금 전';
  } else if (elapsedTime < 3600) {
    elapsedTime = Math.round(elapsedTime / 60);
    calTime = `${elapsedTime}분 전`;
  } else if (elapsedTime < 43200) {
    elapsedTime = Math.round(elapsedTime / 3600);
    calTime = `${elapsedTime}시간 전`;
  } else if (elapsedTime < 129600) {
    elapsedTime = Math.round(elapsedTime / 43200);
    calTime = `${elapsedTime}일 전`;
  }
  return calTime;
}

export default CalElapsedTime;
