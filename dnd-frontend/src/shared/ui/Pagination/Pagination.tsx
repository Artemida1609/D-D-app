interface PaginationProps {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
}

export const Pagination = ({ currentPage, totalPages, onPageChange }: PaginationProps) => {
  const getPageNumbers = () => {
    const pages: (number | string)[] = [];
    
    if (totalPages <= 5) {
      for (let i = 1; i <= totalPages; i++) {
        pages.push(i);
      }
    } else {
      pages.push(1);
      
      if (currentPage > 3) {
        pages.push("...");
      }
      
      const start = Math.max(2, currentPage - 1);
      const end = Math.min(totalPages - 1, currentPage + 1);
      
      for (let i = start; i <= end; i++) {
        pages.push(i);
      }
      
      if (currentPage < totalPages - 2) {
        pages.push("...");
      }
      
      pages.push(totalPages);
    }
    
    return pages;
  };

  return (
    <div className="flex justify-center items-center gap-2 py-8 mt-auto">
      <button 
        onClick={() => onPageChange(currentPage - 1)}
        disabled={currentPage === 1}
        className="w-10 h-10 border border-[#FFFBE4] text-[#FFFBE4] rounded-[8px] disabled:opacity-30 disabled:cursor-not-allowed hover:bg-[#FFFBE4] hover:text-[#00192D] transition flex items-center justify-center"
      >
        {"<"}
      </button>
      
      <div className="flex gap-1 overflow-x-auto max-w-[50vw]">
        {getPageNumbers().map((page, index) => (
          typeof page === "string" ? (
            <span key={`ellipsis-${index}`} className="w-10 h-10 flex items-center justify-center text-[#FFFBE4] opacity-50">
              {page}
            </span>
          ) : (
            <button
              key={page}
              onClick={() => onPageChange(page)}
              className={`w-10 h-10 rounded-[8px] font-medium transition border flex items-center justify-center ${
                currentPage === page 
                  ? 'bg-[#FFFBE4] text-[#00192D] border-[#FFFBE4]' 
                  : 'bg-transparent text-[#FFFBE4] border-[#FFFBE4] opacity-50 hover:opacity-100'
              }`}
            >
              {page}
            </button>
          )
        ))}
      </div>

      <button 
        onClick={() => onPageChange(currentPage + 1)}
        disabled={currentPage === totalPages}
        className="w-10 h-10 border border-[#FFFBE4] text-[#FFFBE4] rounded-[8px] disabled:opacity-30 disabled:cursor-not-allowed hover:bg-[#FFFBE4] hover:text-[#00192D] transition flex items-center justify-center"
      >
        {">"}
      </button>
    </div>
  );
};
