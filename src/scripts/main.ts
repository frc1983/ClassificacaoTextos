const WHATSAPP_NUMBER = '5551997846870';
const ADS_CONVERSION = 'AW-18337213268/y8GVCNbGl-EcENTW7qdE';

function gtagEvent(name: string, params?: Record<string, unknown>) {
  const gtag = (window as unknown as { gtag?: (...args: unknown[]) => void }).gtag;
  if (typeof gtag === 'function') gtag('event', name, params);
}

function trackWhatsAppConversion() {
  gtagEvent('conversion', {
    send_to: ADS_CONVERSION,
    value: 1.0,
    currency: 'BRL',
  });
}

function initNavbar() {
  const navbar = document.getElementById('mv-navbar');
  if (!navbar) return;
  const onScroll = () => {
    navbar.classList.toggle('scrolled', window.scrollY > 60);
  };
  onScroll();
  window.addEventListener('scroll', onScroll, { passive: true });
}

function initMobileMenu() {
  const menuBtn = document.getElementById('mv-menu-btn');
  const mobileMenu = document.getElementById('mv-mobile-menu');
  if (!menuBtn || !mobileMenu) return;
  menuBtn.addEventListener('click', () => {
    mobileMenu.classList.toggle('open');
  });
  mobileMenu.querySelectorAll('a').forEach((link) => {
    link.addEventListener('click', () => mobileMenu.classList.remove('open'));
  });
}

function initSmoothScroll() {
  document.querySelectorAll<HTMLAnchorElement>('a[href^="#"]').forEach((anchor) => {
    anchor.addEventListener('click', (e) => {
      const href = anchor.getAttribute('href');
      if (!href || href === '#') return;
      const target = document.querySelector(href);
      if (!target) return;
      e.preventDefault();
      target.scrollIntoView({ behavior: 'smooth', block: 'start' });
    });
  });
}

function initHeroCarousel() {
  const slides = document.querySelectorAll('.mv-hero-slide');
  const dots = document.querySelectorAll('.mv-hero-dot');
  const heroSection = document.getElementById('mv-hero');
  const heroBg = document.querySelector('.mv-hero-bg');
  if (slides.length === 0) return;

  let currentSlide = 0;
  let heroInterval: number | undefined;

  const showSlide = (idx: number) => {
    slides.forEach((s, i) => s.classList.toggle('active', i === idx));
    dots.forEach((d, i) => d.classList.toggle('active', i === idx));
    currentSlide = idx;
  };

  const nextSlide = () => showSlide((currentSlide + 1) % slides.length);

  const restartHeroAutoplay = () => {
    window.clearInterval(heroInterval);
    heroInterval = window.setInterval(nextSlide, 5000);
  };

  showSlide(0);
  if (slides.length < 2) return;

  restartHeroAutoplay();
  dots.forEach((dot, i) => {
    dot.addEventListener('click', () => {
      showSlide(i);
      restartHeroAutoplay();
    });
  });

  if (heroSection) {
    heroBg?.classList.add('is-clickable');
    heroSection.addEventListener('click', (e) => {
      const target = e.target as HTMLElement;
      if (target.closest('a, button')) return;
      nextSlide();
      restartHeroAutoplay();
    });
  }
}

function initContactForm() {
  const contactForm = document.getElementById('mv-contact-form') as HTMLFormElement | null;
  const successMsg = document.getElementById('mv-form-success');
  const submitBtn = document.getElementById('mv-form-submit') as HTMLButtonElement | null;
  if (!contactForm || !submitBtn) return;

  contactForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const name = (document.getElementById('mv-name') as HTMLInputElement).value.trim();
    const phone = (document.getElementById('mv-phone') as HTMLInputElement).value.trim();
    const email = (document.getElementById('mv-email') as HTMLInputElement).value.trim();
    const message = (document.getElementById('mv-message') as HTMLTextAreaElement).value.trim();

    if (!name || !phone || !email || !message) {
      alert('Por favor, preencha todos os campos.');
      return;
    }
    if (name.length < 2) {
      alert('Nome deve ter pelo menos 2 caracteres.');
      return;
    }
    if (phone.length < 10) {
      alert('Telefone inválido.');
      return;
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      alert('E-mail inválido.');
      return;
    }
    if (message.length < 10) {
      alert('Mensagem deve ter pelo menos 10 caracteres.');
      return;
    }

    const text = [
      'Olá! Gostaria de solicitar um orçamento.',
      '',
      `Nome: ${name}`,
      `Telefone: ${phone}`,
      `E-mail: ${email}`,
      '',
      message,
    ].join('\n');

    const url = `https://wa.me/${WHATSAPP_NUMBER}?text=${encodeURIComponent(text)}`;
    if (successMsg) successMsg.style.display = 'block';
    contactForm.reset();
    trackWhatsAppConversion();
    window.setTimeout(() => window.open(url, '_blank'), 400);
  });
}

function initScrollAnimations() {
  if (!('IntersectionObserver' in window)) return;
  const observer = new IntersectionObserver(
    (entries) => {
      entries.forEach((entry) => {
        if (!entry.isIntersecting) return;
        const el = entry.target as HTMLElement;
        el.style.opacity = '1';
        el.style.transform = 'translateY(0)';
      });
    },
    { threshold: 0.1 },
  );

  document.querySelectorAll('.mv-problem-card, .mv-diff-card, .mv-service-card, .mv-test-card').forEach((el) => {
    const node = el as HTMLElement;
    node.style.opacity = '0';
    node.style.transform = 'translateY(20px)';
    node.style.transition = 'opacity 0.5s ease, transform 0.5s ease';
    observer.observe(node);
  });
}

document.addEventListener(
  'click',
  (event) => {
    const target = event.target as HTMLElement | null;
    const link = target?.closest('a[href*="wa.me"]');
    if (link) trackWhatsAppConversion();
  },
  true,
);

initNavbar();
initMobileMenu();
initSmoothScroll();
initHeroCarousel();
initContactForm();
initScrollAnimations();
