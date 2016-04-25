\LoadClass[10pt,a4paper,openany]{book}
\usepackage[utf8]{inputenc}
\usepackage[english]{babel}
\usepackage{ucs}
\usepackage[pdftex]{graphicx}
\usepackage{fullpage}
\usepackage{here}
\usepackage{array}
\usepackage{xspace}
\usepackage{hyperref}
\setlength{\parindent}{0cm}
%\setlength{\parskip}{1ex plus 0.5ex minus 0.2ex}


\setlength{\parindent}{0.5cm}

%redefinition of the color of the links
\hypersetup{
    colorlinks,
    citecolor=black,
    filecolor=black,
    linkcolor=black,
    urlcolor=black
}

%redefinition de la présentation des parties (inspiré de amsbook.cls)

\def\part{\cleardoublepage \thispagestyle{empty}%
  \null\vfil  \markboth{}{}\secdef\@part\@spart}
\def\@part[#1]#2{%
  \ifnum \c@secnumdepth >-2\relax \refstepcounter{part}%
    \addcontentsline{toc}{part}{\partname\ \thepart.
    \protect\enspace\protect\noindent#1}%
  \else
    \addcontentsline{toc}{part}{#1}\fi
  \begingroup\centering
  \ifnum \c@secnumdepth >-2\relax
       {\fontsize{\@xviipt}{22}\bfseries
     \partname\ \Large\thepart} \vskip 20\p@ \fi
  \fontsize{\@xxpt}{25}\bfseries
      \Huge\textsf{#1}\vfil\vfil\endgroup \newpage\thispagestyle{empty}}


\def\@spart#1{\addcontentsline{toc}{part}{\protect\noindent#1}%
  \begingroup\centering
  \fontsize{\@xxpt}{25}\bfseries
     \Huge\textsf{#1}\vfil\vfil\endgroup \newpage\thispagestyle{empty}}
\def\partrunhead#1#2#3{%
  \@ifnotempty{#2}{\uppercase{\ignorespaces#1 #2\unskip}\@ifnotempty{#3}{. }}%
  \def\@tempa{#3}%
  \ifx\@empty\@tempa\else
    \begingroup \def\\{ \ignorespaces}% defend against questionable usage
    \uppercasenonmath\@tempa\@tempa
    \endgroup
  \fi
}
%------------------------------------------------------------
%redefinition de la présentation des chapitres (inspiré de amsbook.cls)
%\renewcommand{\thechapter}{\roman{chapter}}
\def\@makechapterhead#1{%\global\topskip 7.5pc\relax
  \begingroup
  \fontsize{\@xivpt}{18}\sffamily\scshape\centering
    \ifnum\c@secnumdepth>\m@ne
      \leavevmode \hskip-\leftskip
      \rlap{\vbox to\z@{\vss
      \centerline{\LARGE\mdseries
          \uppercase\@xp{\chaptername}\enspace\thechapter}
      \vskip 3pc}}\hskip\leftskip\fi
     \Huge\textsf{#1}\par\vspace{1cm}\hrule\vspace{1cm} \endgroup
  \skip@34\p@ \advance\skip@-\normalbaselineskip
  \vskip\skip@ }

\def\@makeschapterhead#1{%\global\topskip 7.5pc\relax
  \begingroup
  \fontsize{\@xivpt}{18}\sffamily\scshape\centering
  \Huge\textsf{#1}\par\vspace{1cm}\hrule\vspace{1cm} \endgroup
  \skip@34\p@ \advance\skip@-\normalbaselineskip
  \vskip\skip@ }
