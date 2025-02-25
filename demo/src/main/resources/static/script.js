document.addEventListener("DOMContentLoaded", function () {
    const sliders = document.querySelectorAll('.slider-container');

    sliders.forEach(slider => {
        const sliderWrapper = slider.querySelector('.slider-wrapper');
        let isHovered = false;
        let scrollInterval;

        function startSlider() {
            scrollInterval = setInterval(() => {
                if (!isHovered) {
                    sliderWrapper.scrollLeft += 1;
                    if (sliderWrapper.scrollLeft >= sliderWrapper.scrollWidth - sliderWrapper.clientWidth) {
                        sliderWrapper.scrollLeft = 0;
                    }
                }
            }, 15); // Ajusta la velocidad del carrusel (más pequeño = más rápido)
        }

        function stopSlider() {
            clearInterval(scrollInterval);
        }

        sliderWrapper.addEventListener('mouseenter', () => {
            isHovered = true;
            stopSlider();
        });

        sliderWrapper.addEventListener('mouseleave', () => {
            isHovered = false;
            startSlider();
        });

        startSlider();
    });
});