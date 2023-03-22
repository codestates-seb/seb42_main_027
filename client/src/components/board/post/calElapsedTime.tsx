function CalElapsedTime(time: string) {
  const startTime = new Date(time).getTime();
  const endTime = Date.now();
  const elapsedTime = (endTime - startTime) / 1000;
  return elapsedTime;
}

export default CalElapsedTime;
