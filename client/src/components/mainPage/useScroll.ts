import { useState, useEffect } from 'react';

const useScroll = (scrollThreshold: number) => {
  const [scrollPosition, setScrollPosition] = useState(0);

  const handleScroll = () => {
    const currentScrollPosition = window.pageYOffset;
    setScrollPosition(currentScrollPosition);
  };

  useEffect(() => {
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);

  const isScrolled = scrollPosition > scrollThreshold;

  return { isScrolled, scrollPosition };
};

export default useScroll;
