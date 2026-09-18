export type CardItem = {
  title: string;
  desc: string;
  icon: string;
};

export type MediaItem =
  | { type: 'image'; src: string; alt: string }
  | { type: 'video'; src: string; poster: string; alt: string };

export type ServiceItem = {
  title: string;
  desc: string;
  media: MediaItem;
};

export type GalleryItem = {
  src: string;
  alt: string;
};

export type Testimonial = {
  name: string;
  initial: string;
  date: string;
  text: string;
};

export type HeroSlide = {
  src: string;
  position?: string;
};

export type ServicePage = {
  heroClass: string;
  badge: string;
  titleHtml: string;
  subtitle: string;
  slides: HeroSlide[];
  problemsIntro: string;
  problems: CardItem[];
  diffsIntro: string;
  diffs: CardItem[];
  servicesIntro: string;
  services: ServiceItem[];
  galleryIntro: string;
  gallery: GalleryItem[];
  testimonials: Testimonial[];
  aboutTitle: string;
  aboutParagraphs: string[];
  aboutChecks: string[];
  aboutImage: string;
  aboutCrossLink: { href: string; label: string };
};
