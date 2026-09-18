# Manejo Vertical

Site institucional estático da [Manejo Vertical](https://manejovertical.com.br/), recriado em **Astro** a partir do WordPress (tema `astra-child-manejo`). Sem PHP, banco ou painel: o conteúdo fica em arquivos de código para manutenção por IA.

**IA:** leia [`AGENTS.md`](./AGENTS.md) antes de alterar o código (regras, mapa de arquivos, o que não fazer).

## Páginas

| Rota | Arquivo | Conteúdo |
| --- | --- | --- |
| `/` | `src/pages/index.astro` | Hub: escolha entre os dois serviços |
| `/manutencao-predial/` | `src/pages/manutencao-predial.astro` | Landing de manutenção predial |
| `/poda-arvores/` | `src/pages/poda-arvores.astro` | Landing de poda de árvores |

A URL antiga `/manutencao-predial-em-altura-compromisso-com-a-qualidade/` redireciona para `/manutencao-predial/`.

## Onde editar textos e mídia

Playbook para outra IA: [`AGENTS.md`](./AGENTS.md). Textos e dados editáveis estão em `src/data/`:

- `site.ts` — nome, WhatsApp, Instagram, SEO, tags do Google Ads
- `home.ts` — textos da home
- `predial.ts` — página de manutenção predial
- `poda.ts` — página de poda
- `types.ts` — formato dos dados

Imagens e vídeos ficam em `public/images/` e `public/videos/`. Estilos em `src/styles/global.css`. Interações (menu, carrossel, formulário) em `src/scripts/main.ts`.

## Formulário e WhatsApp

O formulário **não usa backend**. Ele monta a mensagem e abre `https://wa.me/5551997846870`. Cliques em links `wa.me` disparam a conversão do Google Ads (`AW-18337213268/y8GVCNbGl-EcENTW7qdE`).

## Desenvolvimento

```bash
npm install
npm run dev
```

Abre em `http://localhost:4321/`.

```bash
npm run build
```

Gera HTML estático em `dist/`.

## Publicar na Hostinger (cutover)

1. No WordPress/Hostinger: **backup completo** (arquivos + banco).
2. Neste projeto: `npm run build`.
3. No Gerenciador de Arquivos ou FTP da Hostinger, esvazie `public_html` (depois do backup) e envie **o conteúdo** de `dist/` (incluindo `.htaccess`).
4. Confira SSL do domínio `manejovertical.com.br`.
5. Teste:
   - `/`, `/manutencao-predial/`, `/poda-arvores/`
   - redirect da URL longa
   - botões e formulário do WhatsApp
   - layout no celular
6. Só então desative WordPress/plugins para não manter PHP sem necessidade.

O `.htaccess` em `public/` já cobre HTTPS, barra no final, 301 da URL antiga e página 404.

## Repositório GitHub e pasta local

Destino do projeto:

- GitHub: https://github.com/nathanabreu/manejovertical.git
- Pasta Windows: `C:\Programas\GIT\manejo_vertical`

Este agente **não consegue enviar commits** para `nathanabreu/manejovertical` (o GitHub responde 404 ao token da sessão — em geral o repositório é privado e o GitHub App do Cursor ainda não tem acesso). Por isso o site também está numa **branch limpa** (um único commit, sem o histórico Java do `ClassificacaoTextos`):

https://github.com/frc1983/ClassificacaoTextos/tree/cursor/manejovertical-eed9

**Não faça merge** da PR do `ClassificacaoTextos` no `master` Java.

### Popular a pasta local e o repositório novo

No PowerShell:

```powershell
cd C:\Programas\GIT\manejo_vertical

git remote -v
git remote set-url origin https://github.com/nathanabreu/manejovertical.git

git fetch https://github.com/frc1983/ClassificacaoTextos.git cursor/manejovertical-eed9
git checkout -B main FETCH_HEAD

git push -u origin main
```

Se o GitHub recusar por causa de um README inicial no repositório vazio:

```powershell
git push -u origin main --force
```

Depois, na pasta:

```powershell
npm install
npm run dev
```

Para o agente conseguir dar `git push` direto nesse repositório da próxima vez: no GitHub, conceda ao **Cursor GitHub App** acesso a `nathanabreu/manejovertical` (Settings → Applications → Cursor → Repository access).
