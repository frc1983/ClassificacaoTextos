import type { CardItem } from './types';

export const homeHero = {
  predialBg: '/images/hero-home-manutencao-predial.jpg',
  podaBg: '/images/hero-home-poda-arvores.jpg',
  badge: 'NR-35 | Trabalho em Altura Seguro',
  titleHtml: 'Manejo Vertical<br /><span class="highlight">Soluções em Altura</span>',
  subtitle:
    'Equipe qualificada em acesso por cordas e trabalhos em altura conforme a NR-35, unindo segurança, agilidade e técnica certificada. Atendemos Porto Alegre, Região Metropolitana e Interior do RS.',
};

export const homeChoices = [
  {
    href: '/manutencao-predial/',
    title: 'Manutenção Predial em Altura',
    desc: 'Dreno de ar-condicionado, jardim vertical, limpeza de vidros, revitalização de fachadas, impermeabilização e instalações em altura.',
    cta: 'Ver Manutenção Predial',
    variant: 'predial' as const,
    icon: 'building',
  },
  {
    href: '/poda-arvores/',
    title: 'Poda de Árvores em Altura',
    desc: 'Poda, supressão e manejo de árvores urbanas sem uso de esporas, com técnica certificada e acesso por cordas.',
    cta: 'Ver Poda de Árvores',
    variant: 'poda' as const,
    icon: 'tree',
  },
];

export const homeTrust: CardItem[] = [
  {
    title: 'Segurança NR-35',
    desc: 'Equipe treinada e certificada em trabalho em altura, com procedimentos seguros em cada projeto.',
    icon: 'shield',
  },
  {
    title: 'Atendimento Direto',
    desc: 'Você fala direto com o responsável técnico, sem intermediários.',
    icon: 'people',
  },
  {
    title: 'Experiência Prática',
    desc: '+5 anos e +50 projetos executados em Porto Alegre e região.',
    icon: 'check',
  },
];
