import type { ServicePage } from './types';

export const podaPage: ServicePage = {
  heroClass: 'mv-hero--poda',
  badge: 'Poda Técnica em Altura',
  titleHtml: 'Poda em Altura<br /><span class="highlight">com Segurança e Agilidade</span>',
  subtitle:
    'Equipe especializada em poda e manejo de árvores, em altura ou no solo, com segurança em cada etapa do trabalho.',
  slides: [
    { src: '/images/remocao-arvores.jpg', position: 'center 20%' },
    { src: '/images/poda-escalada-tecnica.jpg', position: 'center 20%' },
    { src: '/images/manejo-arvores-urbanas.jpg', position: 'center 20%' },
    { src: '/images/poda-arvore.jpg', position: 'center 20%' },
    { src: '/images/poda-arvore-altura-2.jpg', position: 'center 20%' },
  ],
  problemsIntro:
    'Identificamos e solucionamos os riscos mais comuns em árvores urbanas com técnica, segurança e respeito à saúde do vegetal.',
  problems: [
    { icon: 'bolt', title: 'Árvores com Risco de Queda', desc: 'Galhos sobre fiação, telhados ou áreas de circulação, e árvores com risco de queda comprometem a segurança do local. Poda e manejo técnico eliminam o risco sem prejudicar a saúde da árvore.' },
    { icon: 'tree', title: 'Galhos sobre Fiação e Telhados', desc: 'Galhos próximos à rede elétrica ou apoiados sobre telhados são risco constante de acidente e dano patrimonial. Fazemos a poda de segurança com acesso por cordas.' },
    { icon: 'medal', title: 'Copa Densa e Falta de Luminosidade', desc: 'Copas muito densas reduzem a luminosidade e a ventilação do imóvel e do jardim. O raleio técnico da copa resolve sem descaracterizar a árvore.' },
    { icon: 'drop', title: 'Raízes Comprometendo Calçadas e Estruturas', desc: 'Raízes superficiais podem danificar calçadas, muros e tubulações. Avaliamos a intervenção correta sem comprometer a estabilidade da árvore.' },
    { icon: 'tool', title: 'Poda Mal Executada em Gestões Anteriores', desc: 'Cortes malfeitos e poda drástica deixam a árvore vulnerável a pragas e doenças. Fazemos o manejo corretivo com técnica que favorece a recuperação.' },
    { icon: 'shield', title: 'Acesso Difícil sem Andaime ou Guindaste', desc: 'Árvores altas ou em locais de acesso restrito exigem técnica de cordas e cesto aéreo, sem depender de guindaste ou estruturas complexas.' },
  ],
  diffsIntro:
    'Combinamos técnica especializada, equipamentos adequados e atendimento direto para entregar resultados duradouros, preservando a saúde da árvore.',
  diffs: [
    { icon: 'tree', title: 'Poda sem Esporas', desc: 'Realizamos poda e manejo de árvores urbanas sem o uso de esporas, preservando a saúde e a integridade do vegetal. Técnica correta que garante recuperação mais rápida e menor risco de doenças.' },
    { icon: 'info', title: 'Acesso por Cordas e Cesto Aéreo', desc: 'Técnicas que permitem atuar em locais de difícil alcance com agilidade, menor custo operacional e sem interferência na rotina do local.' },
    { icon: 'shield', title: 'Segurança NR-35', desc: 'Todo serviço é conduzido com foco em procedimentos seguros e atuação responsável em trabalho em altura, em conformidade com a NR-35.' },
    { icon: 'medal', title: 'Diagnóstico Completo', desc: 'Antes de qualquer corte, avaliamos a saúde e a estrutura da árvore para aplicar a técnica de poda mais adequada, com resultado duradouro.' },
    { icon: 'check', title: 'Experiência Prática', desc: 'Atuação voltada a problemas reais de árvores urbanas, com visão técnica, execução especializada e foco total em entrega e resultado.' },
    { icon: 'people', title: 'Atendimento Direto', desc: 'Você fala diretamente com o responsável técnico. Sem intermediários, com avaliação objetiva e solução alinhada à necessidade real.' },
  ],
  servicesIntro:
    'Poda, supressão e manejo de árvores urbanas em altura, com técnica certificada e equipamentos adequados, atendendo Porto Alegre, Canoas e região metropolitana.',
  services: [
    { title: 'Poda por Escalada Técnica (Tree Climbing)', desc: 'O profissional sobe na própria árvore com corda e equipamentos de segurança certificados, se fixando com firmeza antes de cada corte. Técnica precisa e segura, sem uso de esporas — ideal para árvores altas ou em locais sem espaço para máquinas.', media: { type: 'video', src: '/videos/whatsapp-video-2026-08-08-at-21.58.42.mp4', poster: '/images/hero-poda-5.jpg', alt: 'Poda por Escalada Técnica (Tree Climbing)' } },
    { title: 'Manejo de Árvores Urbanas', desc: 'Avaliação da copa, raízes e estrutura da árvore para equilibrar segurança, saúde do vegetal e convivência com o espaço urbano, conforme porte e espécie.', media: { type: 'image', src: '/images/manejo-arvores-urbanas.jpg', alt: 'Manejo de Árvores Urbanas' } },
    { title: 'Remoção de Árvores', desc: 'Corte e remoção controlada por cordas em locais de difícil acesso, sem espaço para queda livre, minimizando risco a pessoas, veículos e estruturas.', media: { type: 'image', src: '/images/remocao-arvores.jpg', alt: 'Remoção de Árvores' } },
    { title: 'Laudo Ambiental com ART', desc: 'Apoio na elaboração do laudo técnico com ART junto a profissional parceiro habilitado (biólogo, engenheiro agrônomo ou florestal) — documento exigido pela SMAMUS para autorizar poda (AEPV) ou remoção (AERV) em Porto Alegre.', media: { type: 'image', src: '/images/laudo-ambiental.jpg', alt: 'Laudo Ambiental com ART' } },
    { title: 'Cesto Aéreo', desc: 'Uso de cesto aéreo para agilizar poda e remoção em locais com acesso viário, elevando o profissional com comando próprio dentro do cesto, em conformidade com a NR-12 – Anexo XII.', media: { type: 'image', src: '/images/cesto-aereo.jpg', alt: 'Cesto Aéreo' } },
    { title: 'Segurança e Normas Técnicas', desc: 'Toda poda e remoção segue as normas vigentes: NR-35 (trabalho em altura), NR-38 (segurança na poda de árvores), NR-12 – Anexo V (operação de motosserra) e NR-12 – Anexo XII (operação de cesto aéreo), com equipe treinada, planejamento de risco e equipamentos de proteção certificados.', media: { type: 'image', src: '/images/seguranca-normas-tecnicas.jpg', alt: 'Segurança e Normas Técnicas' } },
    { title: 'Rigging e Descida Controlada de Galhos', desc: 'Sistema de roldanas e cordas para descer galhos e toras com peso controlado, evitando queda livre e protegendo jardins, telhados, cercas e estruturas próximas durante o corte.', media: { type: 'video', src: '/videos/rigging-descida-controlada.mp4', poster: '/images/poda-escalada-tecnica.jpg', alt: 'Rigging e Descida Controlada de Galhos' } },
    { title: 'Poda de Palmeiras', desc: 'Poda e limpeza de palmeiras com acesso por cordas, remoção de folhas secas e cachos, sem uso de esporas — técnica que não danifica o estipe e preserva a saúde da palmeira.', media: { type: 'image', src: '/images/poda-de-palmeiras.jpg', alt: 'Poda de Palmeiras' } },
  ],
  galleryIntro:
    'Conheça os trabalhos de poda e manejo de árvores urbanas realizados pela equipe da Manejo Vertical em Porto Alegre e região.',
  gallery: [
    { src: '/images/whatsapp-image-2026-07-21-at-21.41.29-1.jpeg', alt: 'Poda em altura - Cliente: Sede Campestre GM (Gravataí-RS)' },
    { src: '/images/20260725_114357-scaled.jpg', alt: 'Poda Técnica em área urbana - Canoas - RS' },
    { src: '/images/20260725_111652-scaled.jpg', alt: 'Poda Técnica em área urbana - Canoas - RS' },
    { src: '/images/img_20250708_094444_299.webp', alt: 'Poda Técnica em área urbana - Porto Alegre - RS' },
    { src: '/images/img_20260415_094700_304.jpg', alt: 'Poda Técnica em área urbana - Porto Alegre - RS' },
  ],
  testimonials: [
    { name: 'Lúcia Beatriz Vaz', initial: 'L', date: 'Abril 2026', text: 'O Bruno faz a poda das minhas árvores há 4 anos, gosto muito do trabalho dele, sempre satisfatório. Recomendo!' },
    { name: 'Valdemar Sussbier', initial: 'V', date: 'Julho 2026', text: 'Todo trabalho contratado foi efetuado com êxito. Pontual nos horários combinados do trabalho! Atuaram com profissionalidade toda a equipe de trabalho. Possuíam as ferramentas e equipamentos necessários e com uma excelente educação no tratamento.' },
    { name: 'Pedro de Araújo', initial: 'P', date: 'Maio de 2026', text: 'Excelente trabalho, profissionalismo e qualidade.' },
    { name: 'Joel Alexandre', initial: 'J', date: 'Março 2026', text: 'Serviço com qualidade ótimo profissional' },
  ],
  aboutTitle: 'Especialistas em Poda e Manejo de Árvores Urbanas',
  aboutParagraphs: [
    'A Manejo Vertical também é especializada em poda técnica e manejo de árvores urbanas em altura, com atuação em Porto Alegre, Região Metropolitana e Interior do Rio Grande do Sul.',
    'Nossa equipe é treinada e certificada em trabalho em altura conforme a NR-35, realizando a poda por acesso de cordas — sem esporas — para preservar a saúde e a integridade da árvore.',
  ],
  aboutChecks: [
    'Certificação NR-35 — Trabalho em Altura',
    'Certificação NR-38 — Segurança na Poda de Árvores',
    'Certificação NR-12 — Motosserra e Cesto Aéreo',
    'Poda técnica sem uso de esporas',
    'Acesso por cordas e cesto aéreo',
    'Diagnóstico da saúde da árvore antes da poda',
    'Emissão de Laudo Ambiental com ART',
    'Cobertura em Porto Alegre, Região Metropolitana e Interior do RS',
  ],
  aboutImage: '/images/poda-arvore.jpg',
  aboutCrossLink: { href: '/manutencao-predial/', label: 'Também fazemos manutenção predial em altura →' },
};
