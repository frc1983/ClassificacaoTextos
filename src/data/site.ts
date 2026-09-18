export const site = {
  name: 'Manejo Vertical',
  url: 'https://manejovertical.com.br',
  phoneDisplay: '+55 51 99784-6870',
  whatsappNumber: '5551997846870',
  whatsappDefaultText: 'Olá! Gostaria de solicitar um orçamento.',
  instagram: 'https://instagram.com/manejovertical',
  instagramHandle: '@manejovertical',
  googleReviews: 'https://g.co/kgs/85dc6bac089b0cc3',
  googleMaps:
    'https://www.google.com/maps/place/Manejo+Vertical:+Manuten%C3%A7%C3%A3o+Predial+em+Altura+%26+Poda+de+%C3%81rvores/@-30.4166972,-53.6699156,7z',
  adsId: 'AW-18337213268',
  adsConversion: 'AW-18337213268/y8GVCNbGl-EcENTW7qdE',
  gtagId: 'GT-TBBK62CR',
  area: 'Porto Alegre, Região Metropolitana e Interior do RS',
  favicon: '/images/cropped-profile_square_navy_v2-32x32.png',
  appleTouchIcon: '/images/cropped-profile_square_navy_v2-180x180.png',
  icon192: '/images/cropped-profile_square_navy_v2-192x192.png',
};

export function waLink(text = site.whatsappDefaultText): string {
  return `https://wa.me/${site.whatsappNumber}?text=${encodeURIComponent(text)}`;
}

export const stats = [
  { num: '+5', label: 'Anos de Experiência' },
  { num: '+50', label: 'Serviços Concluídos' },
  { num: '5.0', label: 'Avaliação Google' },
];

export type NavLink = { href: string; label: string };

export const homeNav: NavLink[] = [
  { href: '/#hero', label: 'Início' },
  { href: '/manutencao-predial/', label: 'Manutenção Predial' },
  { href: '/poda-arvores/', label: 'Poda de Árvores' },
];

export const predialNav: NavLink[] = [
  { href: '/', label: 'Início' },
  { href: '#services', label: 'Serviços' },
  { href: '#gallery', label: 'Trabalhos' },
  { href: '#testimonials', label: 'Depoimentos' },
  { href: '#about', label: 'Sobre' },
  { href: '/poda-arvores/', label: 'Poda de Árvores' },
  { href: '#contact', label: 'Contato' },
];

export const podaNav: NavLink[] = [
  { href: '/', label: 'Início' },
  { href: '#services', label: 'Serviços' },
  { href: '#gallery', label: 'Trabalhos' },
  { href: '#testimonials', label: 'Depoimentos' },
  { href: '#about', label: 'Sobre' },
  { href: '/manutencao-predial/', label: 'Manutenção Predial' },
  { href: '#contact', label: 'Contato' },
];

export const homeSeo = {
  title: 'Manejo Vertical | Soluções em Altura — Manutenção Predial e Poda de Árvores',
  description:
    'Manutenção predial em altura e poda de árvores em Porto Alegre. Dreno de ar-condicionado, jardim vertical, limpeza de vidros, fachadas e poda técnica sem esporas. Segurança NR-35.',
  path: '/',
};

export const predialSeo = {
  title: 'Manutenção Predial em Altura - manejovertical.com.br',
  description:
    'Manutenção predial em altura em Porto Alegre: revitalização e limpeza de fachadas, jardim vertical, dreno de ar-condicionado e muito mais. NR-35 e orçamento grátis.',
  path: '/manutencao-predial/',
};

export const podaSeo = {
  title: 'Poda de Árvores em Altura Porto Alegre | Manejo Vertical',
  description:
    'Poda de árvores em altura em Porto Alegre: poda técnica sem esporas, manejo de árvores urbanas e supressão com acesso por cordas. Técnica certificada, segurança NR-35. Solicite avaliação gratuita!',
  path: '/poda-arvores/',
};
