function leftPad(value: number) {
  if (value >= 10) {
    return value;
  }

  return `0${value}`;
}

function CalElapsedTime(time: string) {
  const timeBuffer = new Date(time);
  let calTime = '';
  const startTime = timeBuffer.getTime();
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
  } else {
    const year = timeBuffer.getFullYear();
    const month = leftPad(timeBuffer.getMonth() + 1);
    const date = leftPad(timeBuffer.getDate());
    const hour = leftPad(timeBuffer.getHours());
    const min = leftPad(timeBuffer.getMinutes());
    const sec = leftPad(timeBuffer.getSeconds());

    calTime = `${year}-${month}-${date} ${hour}:${min}:${sec}`;
  }

  return calTime;
}

export default CalElapsedTime;
